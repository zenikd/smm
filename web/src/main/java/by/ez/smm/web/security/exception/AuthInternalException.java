package by.ez.smm.web.security.exception;

public class AuthInternalException extends RuntimeException
{
	public AuthInternalException(String message) {
		super(message);
	}

	public AuthInternalException(String message, Throwable cause) {
		super(message, cause);
	}
}
