package ecommerce.security;

import static ecommerce.security.SecurityConstraints.HEADER_STRING;
import static ecommerce.security.SecurityConstraints.SECRET;
import static ecommerce.security.SecurityConstraints.TOKEN_PREFIX;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.Jwts;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter{
	private static final Logger LOG = LoggerFactory.getLogger(JWTAuthorizationFilter.class);		
	
	public JWTAuthorizationFilter(AuthenticationManager authManager) {
		super(authManager);
		LOG.info("Creating authorization filter");
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest req,
									HttpServletResponse res,
									FilterChain chain) throws IOException, ServletException {
		
		LOG.info("Getting header");
		String header = req.getHeader(HEADER_STRING);
		
		if(header == null || !header.startsWith(TOKEN_PREFIX)) {
			LOG.info("Header is null");
			chain.doFilter(req, res);
			return;
		}
		
		UsernamePasswordAuthenticationToken auth = this.getAuthentication(req);
		
		SecurityContextHolder.getContext().setAuthentication(auth);
		chain.doFilter(req, res);
	}
	
	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest req) {
		LOG.info("Authenticating...");
		String token = req.getHeader(HEADER_STRING);
		
		if(token != null) {
			String user = Jwts.parser()
					.setSigningKey(SECRET)
					.parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
					.getBody()
					.getSubject();
			
			if(user != null) {
				UsernamePasswordAuthenticationToken userPassToken =
						new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
				
				return userPassToken;
			}
			LOG.info("User is null");
			return null;
		}
		LOG.info("Tokenn is null");
		return null;
	}
}
