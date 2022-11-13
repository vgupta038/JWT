package com.jwt.SignUpSignInValidateTokenWithRole.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.SignUpSignInValidateTokenWithRole.configuration.JwtTokenUtil;
import com.jwt.SignUpSignInValidateTokenWithRole.dto.JwtResponse;
import com.jwt.SignUpSignInValidateTokenWithRole.dto.LoginRequest;

@RestController
public class SignInController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@PostMapping(value = "/signin")
	public ResponseEntity<?> generateTokenIfAuthenticationIsSuccess(@RequestBody LoginRequest loginRequest) 
															throws Exception {

		Authentication authenticationObject=authenticationManager.authenticate(
						new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		if(authenticationObject!=null) {
			System.out.println("authentication Succesful!!");
		}
		List<String> roles = authenticationObject.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());
		String jwt = jwtTokenUtil.generateJwtToken(loginRequest.getUsername(),loginRequest.getPassword(), roles);
		return ResponseEntity.ok(new JwtResponse(loginRequest.getUsername(),jwt,"Bearer"));
	}
	
}
