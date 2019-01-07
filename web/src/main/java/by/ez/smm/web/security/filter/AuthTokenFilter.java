package by.ez.smm.web.security.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.GenericFilterBean;

import by.ez.smm.web.security.util.TokenUtils;

public class AuthTokenFilter extends GenericFilterBean
{

	private final static String CSRF_TOKEN_HEADER_KEY = "X-Csrf-Token";
	private final static String AUTH_TOKEN_PARAMETER_KEY = "authToken";
	private final static String CSRF_TOKEN_PARAMETER_KEY = "csrfToken";

	private final UserDetailsService userDetailsService;

	@Autowired
	TokenUtils tokenUtils;

	public AuthTokenFilter(UserDetailsService userDetailsService)
	{
		this.userDetailsService = userDetailsService;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
	{
		HttpServletRequest httpRequest = getAsHttpRequest(request);
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		if (isCompromised((HttpServletRequest) request))
		{
			chain.doFilter(request, response);
			return;
		}

		String authToken = extractAuthTokenFromRequest(httpRequest);
		String userName = tokenUtils.getUserNameFromToken(authToken);

		if (userName != null)
		{
			UserDetails userDetails = userDetailsService.loadUserByUsername(userName);

			if (userDetails != null)
			{

				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null,
						userDetails.getAuthorities());
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpRequest));
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		}

		chain.doFilter(request, response);
	}

	private HttpServletRequest getAsHttpRequest(ServletRequest request)
	{
		if (!(request instanceof HttpServletRequest))
		{
			throw new RuntimeException("Expecting an HTTP request");
		}

		return (HttpServletRequest) request;
	}

	private String extractAuthTokenFromRequest(HttpServletRequest httpRequest)
	{
		if (httpRequest.getCookies() != null)
		{
			for (Cookie cookie : httpRequest.getCookies())
			{
				if (cookie.getName().equals(AUTH_TOKEN_PARAMETER_KEY))
				{
					return cookie.getValue();
				}
			}
		}

		throw new BadCredentialsException("Auth token isn't presented");
	}

	private boolean isCompromised(HttpServletRequest request)
	{
		String headerToken = request.getHeader(CSRF_TOKEN_HEADER_KEY);
		if (headerToken == null)
		{
			headerToken = request.getParameter(CSRF_TOKEN_PARAMETER_KEY);
		}

		if (headerToken == null || request.getCookies() == null)
		{
			return true;
		}

		String cookieToken = null;

		for (Cookie cookie : request.getCookies())
		{
			if (cookie.getName().equals("csrf"))
			{
				cookieToken = cookie.getValue();
			}
		}

		return cookieToken == null || !cookieToken.equals(headerToken);
	}
}
