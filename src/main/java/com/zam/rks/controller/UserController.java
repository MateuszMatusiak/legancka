package com.zam.rks.controller;

import com.zam.rks.Service.UserService;
import com.zam.rks.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

	UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

//	@GetMapping("/getUsers")
//	public List<User> getUsers() {
//		return userService.getUsers();
//	}


}
