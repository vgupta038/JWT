package com.jwt.SignUpSignInValidateTokenWithRole.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/moderator")
public class ModerateAPIController {

	@GetMapping(value = "/api")
	public String moderatePing() {
		return "Any Moderate Can Read This";
	}
}
