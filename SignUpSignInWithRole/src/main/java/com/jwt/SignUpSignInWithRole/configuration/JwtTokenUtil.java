package com.jwt.SignUpSignInWithRole.configuration;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil {

	public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

	public String generateJwtToken(String username, List<String> roles) {
		
		return Jwts.builder()
				.setSubject(username)
				.claim("roles", roles)
				.setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime() + JWT_TOKEN_VALIDITY*1000))
				.signWith(SignatureAlgorithm.HS512, "secretValue")
				.compact();
	}
	
}
