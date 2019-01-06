package by.ez.smm.web.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;

import by.ez.smm.web.security.dto.TokenDto;
import by.ez.smm.web.security.util.TokenUtils;

@Controller
@Path("/auth")
public class AuthController
{
	private final static String AUTH_COOKIE_NAME = "authToken";
	private final static String CSRF_COOKIE_NAME = "csrf";
	private final static String COOKIE_PATH = "/smm";

	@Autowired
	@Qualifier("authenticationManager")
	private AuthenticationManager authenticationManager;

	@Autowired
	private TokenUtils tokenUtils;

	@Autowired
	private UserDetailsService userDetailsService;

	@GET
	@Path("/auth")
	public TokenDto authenticate(String jsonData, @Context HttpServletResponse response)
	{
		String username = "";
		String password = "";

		username = "Evgeni";
		password = "Evgeni";

		String token = authenticate(username, password);
		String csrfToken = tokenUtils.generateCsrfToken();
		response.addCookie(createCookie(AUTH_COOKIE_NAME, token, false, true));
		response.addCookie(createCookie(CSRF_COOKIE_NAME, csrfToken, false, false));
		return new TokenDto(token, csrfToken);
	}

	private String authenticate(String username, String password)
	{
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);

		try
		{
			Authentication authentication = authenticationManager.authenticate(authenticationToken);
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		catch (BadCredentialsException e)
		{
			throw new WebApplicationException("Incorrect username or password", e, Response.Status.UNAUTHORIZED);
		}
		catch (LockedException e)
		{
			throw new WebApplicationException("Account is locked", e, Response.Status.BAD_REQUEST);
		}

		/*
		 * Reload user as password of authentication principal will be null after authorization and
		 * password is needed for token generation
		 */
		UserDetails userDetails = userDetailsService.loadUserByUsername(username);
		return tokenUtils.createToken(userDetails, 10000000l);
	}

	private Cookie createCookie(String name, String value, boolean secure, boolean http)
	{
		Cookie cookie = new Cookie(name, value);
		cookie.setSecure(secure);
		cookie.setHttpOnly(http);
		cookie.setPath(COOKIE_PATH);
		return cookie;
	}
}
