package com.zam.rks.controller;

import com.zam.rks.Service.LoginService;
import com.zam.rks.model.LoginCredentials;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

	private final LoginService loginService;

	public LoginController(LoginService loginService) {
		this.loginService = loginService;
	}

	@PostMapping("/login")
	public void login(@RequestBody LoginCredentials credentials) {
	}

	@PutMapping("/register")
	public ResponseEntity<String> register(@RequestBody LoginCredentials user) {
		return loginService.saveNewUser(user);
	}

}
