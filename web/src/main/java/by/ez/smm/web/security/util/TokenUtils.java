package by.ez.smm.web.security.util;

import java.security.SecureRandom;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import by.ez.smm.web.security.exception.AuthInternalException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenUtils
{
	private static final String ALLOWED_CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
	private static final SecureRandom RANDOM = new SecureRandom();

	@Value("${auth.secret.key}")
	private String secretKey;

	public String createToken(UserDetails userDetails, Long expiredTime)
	{
		long expires = System.currentTimeMillis() + 1000 * expiredTime;
		return Jwts.builder()
				.setSubject(userDetails.getUsername())
				.setExpiration(new Date(expires))
				.signWith(SignatureAlgorithm.HS512, secretKey.getBytes())
				.compact();
	}

	public String getUserNameFromToken(String authToken)
	{
		try
		{
			return Jwts.parser()
					.setSigningKey(secretKey.getBytes())
					.parseClaimsJws(authToken)
					.getBody()
					.getSubject();
		}
		catch (JwtException e)
		{
			throw new AuthInternalException("JWT exception", e);
		}
	}

	public String generateCsrfToken()
	{
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < 128; i++)
		{
			sb.append(ALLOWED_CHARACTERS.charAt(RANDOM.nextInt(ALLOWED_CHARACTERS.length())));
		}

		return sb.toString();
	}

}
