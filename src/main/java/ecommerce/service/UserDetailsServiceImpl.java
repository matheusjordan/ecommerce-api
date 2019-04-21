package ecommerce.service;

import static java.util.Collections.emptyList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ecommerce.model.Client;
import ecommerce.repository.ClientRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	private ClientRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		Client c = repository.findByUsername(username);
		
		if(c == null) {
			throw new UsernameNotFoundException(username);
		}
		
		String name = c.getUsername();
		String pass = c.getPassword();
		User user = new User(name, pass, emptyList());
		
		return user;
	}
}
