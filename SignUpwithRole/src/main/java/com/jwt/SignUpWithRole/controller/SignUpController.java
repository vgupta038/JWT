package com.jwt.SignUpWithRole.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.SignUpWithRole.dto.ERole;
import com.jwt.SignUpWithRole.dto.MessageResponse;
import com.jwt.SignUpWithRole.dto.SignUpRequest;
import com.jwt.SignUpWithRole.entity.RoleEntity;
import com.jwt.SignUpWithRole.entity.UserEntity;
import com.jwt.SignUpWithRole.service.RoleEntityService;
import com.jwt.SignUpWithRole.service.UserEntityService;

@RestController
public class SignUpController {

	@Autowired
	private UserEntityService userEntityService;
	
	@Autowired
	private RoleEntityService roleEntityService;
	
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

		Set<String> strRoles = signUpRequest.getRoles();
		Set<RoleEntity> roles = new HashSet<>();

		if (strRoles == null || strRoles.isEmpty()) { // if no roles entered then y default we assign to user role
			RoleEntity userRole = roleEntityService.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					RoleEntity adminRole = roleEntityService.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);
					break;
				case "mod":
					RoleEntity modRole = roleEntityService.findByName(ERole.ROLE_MODERATOR)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(modRole);
					break;
				default:
					RoleEntity userRole = roleEntityService.findByName(ERole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}

		user.setRoles(roles);
		userEntityService.save(user);
		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));

	}
}
