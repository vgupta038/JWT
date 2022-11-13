package com.jwt.SignUpSignInValidateTokenWithRole.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserAPIController {

	@GetMapping(value = "/api")
	public String userPing() {
		return "Any User Can Read This";
	}
	
}
