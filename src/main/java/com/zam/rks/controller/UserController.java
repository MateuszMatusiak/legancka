package com.zam.rks.controller;

import com.zam.rks.Dto.EventDto;
import com.zam.rks.Dto.GroupDto;
import com.zam.rks.Dto.UserDto;
import com.zam.rks.Service.UserService;
import com.zam.rks.model.Group;
import com.zam.rks.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/groups")
	public Set<GroupDto> getGroupsForUser() {
		return userService.getGroupsForUser();
	}

	@PostMapping("/user")
	public User updateUser(@RequestBody UserDto user) {
		return userService.updateUser(user);
	}

	@GetMapping("/user")
	public UserDto getUser() {
		return userService.getUser();
	}

	@GetMapping("/events")
	public Set<EventDto> getEventsForUser() {
		return userService.getEventsForUser();
	}

	@PostMapping("/user/group/{id}")
	public Group setGroupById(@PathVariable int id) {
		return userService.setGroupById(id);
	}
}
