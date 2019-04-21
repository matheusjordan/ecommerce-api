package ecommerce.security;

import static ecommerce.security.SecurityConstraints.EXPIRATION_TIME;
import static ecommerce.security.SecurityConstraints.HEADER_STRING;
import static ecommerce.security.SecurityConstraints.SECRET;
import static ecommerce.security.SecurityConstraints.TOKEN_PREFIX;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import ecommerce.model.Client;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter{
	private final static Logger LOG = LoggerFactory.getLogger(JWTAuthenticationFilter.class);
	
	private AuthenticationManager authenticationManager;
	
	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
		LOG.info("Creating authentication filter");
	}

	//função para validar as credenciais de usuario e authentiicalo
	@Override
	public Authentication attemptAuthentication(HttpServletRequest req,
												HttpServletResponse res) throws RuntimeException {
		
		LOG.info("Attempting Authentication!");
		try {
			LOG.info("Parsing Client");
			Client cred = new ObjectMapper().readValue(req.getInputStream(), Client.class);
			
			String username = cred.getUsername();
			String password = cred.getPassword();
			
			LOG.info("Setting user to authenticate");
			UsernamePasswordAuthenticationToken userPassToken = 
					new UsernamePasswordAuthenticationToken(username, password, new ArrayList<>());
			
			LOG.info("Authenticating user");
			Authentication auth = authenticationManager.authenticate(userPassToken);
			return auth;
			
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	//fução para gerar o token caso o usuario seja authenticado com sucesso
	@Override
	protected void successfulAuthentication(HttpServletRequest req,
											HttpServletResponse res,
											FilterChain chain,
											Authentication auth) throws IOException, ServletException {

		LOG.info("Finding User");
		User user = (User) auth.getPrincipal();
		String username = user.getUsername();
		
		LOG.info("Setting expiration time");
		Date expiration = new Date(System.currentTimeMillis() + EXPIRATION_TIME);
		
		LOG.info("Setting encode algorithm");
		SignatureAlgorithm signAlg = SignatureAlgorithm.HS512;
		
		LOG.info("Creating token");
		String token = Jwts.builder().setSubject(username)
									.setExpiration(expiration)
									.signWith(signAlg, SECRET)
									.compact();
		res.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
		LOG.info("Generated tokken");
	}
}
