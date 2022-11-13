package com.jwt.SignUpSignInValidateTokenWithRole.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.jwt.SignUpSignInValidateTokenWithRole.service.UserEntityService;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private UserEntityService userEntityService;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName();
		String password = authentication.getCredentials().toString();
		UserDetails userDetails = isValidUser(username, password);

		if (userDetails != null) {
			return new UsernamePasswordAuthenticationToken(username, password, userDetails.getAuthorities());
		} else {
			throw new BadCredentialsException("Incorrect user credentials !!");
		}
	}
	
	
	public UserDetails isValidUser(String username, String password) {
		UserDetails userDetails=userEntityService.loadUserByUsername(username);
		
		if (userDetails.getUsername().equalsIgnoreCase(username) &&
				userDetails.getPassword().equals(password)) {
			 return userDetails;
		}
		return null;
	}


	@Override
	public boolean supports(Class<?> authentication) {
		 return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
