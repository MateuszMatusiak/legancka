package com.zam.rks.controller;

import com.zam.rks.Service.UserService;
import com.zam.rks.model.LoginCredentials;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

	private final UserService userService;

	public LoginController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/login")
	public void login(@RequestBody LoginCredentials credentials) {
	}

	@PutMapping("/register")
	public ResponseEntity<String> register(@RequestBody LoginCredentials user) {
		return userService.saveNewUser(user);
	}

}
