package com.jwt.SignUp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.SignUp.dto.MessageResponse;
import com.jwt.SignUp.dto.SignUpRequest;
import com.jwt.SignUp.entity.UserEntity;
import com.jwt.SignUp.service.UserEntityService;


@RestController
public class SignUpController {

	@Autowired
	private UserEntityService userEntityService;
	
	/**
	 * Anyone can hit the API with the new username and password
	 * */
	@PostMapping(value = "/signup")
	public ResponseEntity<?> registerNewUser( @RequestBody SignUpRequest signUpRequest) throws Exception {
		
		if (userEntityService.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}
		
		// Create new userEntity's account
		UserEntity user = new UserEntity(signUpRequest.getUsername(), signUpRequest.getPassword());
		userEntityService.save(user);
		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
}
