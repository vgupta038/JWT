package com.jwt.SignUpSignIn.dto;

public class JwtResponse {

	private final String username;
	private final String jwtAcessToken;
	private final String tokenType;
	
	public JwtResponse(String username, String jwtAcessToken, String tokenType) {
		this.username=username;
		this.jwtAcessToken = jwtAcessToken;
		this.tokenType=tokenType;
	}

	public String getUsername() {
		return username;
	}

	public String getJwtAcessToken() {
		return jwtAcessToken;
	}

	public String getTokenType() {
		return tokenType;
	}
		
}
