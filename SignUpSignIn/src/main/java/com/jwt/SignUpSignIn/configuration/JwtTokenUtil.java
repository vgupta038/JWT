package com.jwt.SignUpSignIn.configuration;

import java.util.Date;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil {

	public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

	public String generateJwtToken(String username) {
		
		return Jwts.builder()
				.setSubject(username)
				.setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime() + JWT_TOKEN_VALIDITY*1000))
				.signWith(SignatureAlgorithm.HS512, "secretValue")
				.compact();
	}
	
}
