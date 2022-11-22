package com.zam.rks.controller;

import com.zam.rks.Service.GroupService;
import com.zam.rks.model.Group;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GroupController {

	private final GroupService groupService;

	public GroupController(GroupService groupService) {
		this.groupService = groupService;
	}

	@PutMapping("/group")
	public ResponseEntity<String> createNewGroup(@RequestBody String groupName) {
		return groupService.createNewGroup(groupName);
	}

	@GetMapping("/group/{id}")
	public List<Group> getGroupById(@PathVariable int id) {
		return groupService.getGroupById(id);
	}

	@PostMapping("/group/{id}")
	public Group updateGroupById(@PathVariable int id, @RequestBody Group group) {
		return groupService.updateGroupById(id, group);
	}

}
