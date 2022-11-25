package com.zam.rks.controller;

import com.zam.rks.Dto.UserDto;
import com.zam.rks.Service.GroupService;
import com.zam.rks.model.Group;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/group")
public class GroupController {

	private final GroupService groupService;

	public GroupController(GroupService groupService) {
		this.groupService = groupService;
	}

	@PutMapping
	public ResponseEntity<String> createNewGroup(@RequestBody String groupName) {
		return groupService.createNewGroup(groupName);
	}

	@PostMapping("/{id}")
	public Group updateGroupById(@PathVariable int id, @RequestBody Group group) {
		return groupService.updateGroupById(id, group);
	}

	@GetMapping("/{id}/users")
	public Set<UserDto> getUsersForGroup(@PathVariable int id) {
		return groupService.getUsersForGroup(id);
	}

}
