package by.ez.smm.web.security.dto;

public class TokenDto
{
	private String token;
	private String csrfToken;

	public TokenDto(String token, String csrfToken)
	{
		this.token = token;
		this.csrfToken = csrfToken;
	}

	public String getToken()
	{
		return this.token;
	}

	public String getCsrfToken()
	{
		return csrfToken;
	}
}
