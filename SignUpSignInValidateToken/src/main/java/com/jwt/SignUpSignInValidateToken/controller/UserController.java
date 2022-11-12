package com.jwt.SignUpSignInValidateToken.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	@GetMapping("/user")
	public String userAccess1() {
		return "User Content.";
	}
}
