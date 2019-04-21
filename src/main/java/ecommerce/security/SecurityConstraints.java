package ecommerce.security;

public class SecurityConstraints {
	public final static String HEADER_STRING = "Authorization";
	public final static String TOKEN_PREFIX = "bearer ";
	public final static String SECRET = "secretkey";
	public final static String SIGN_UP_URL = "/clients/signup";
	public final static String SIGN_IN_URL = "/login";
	
	public final static Long EXPIRATION_TIME = 120000L;
}
