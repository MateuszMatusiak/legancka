package com.zam.rks.Service;

import com.zam.rks.Repository.GroupRepository;
import com.zam.rks.Repository.UserRepository;
import com.zam.rks.model.Group;
import com.zam.rks.model.User;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Scope
public class GroupService {

	private final GroupRepository groupRepository;
	private final UserRepository userRepository;

	public GroupService(GroupRepository groupRepository, UserRepository userRepository) {
		this.groupRepository = groupRepository;
		this.userRepository = userRepository;
	}

	@Transactional
	public ResponseEntity<String> createNewGroup(String groupName) {
		Optional<Group> test = groupRepository.findByName(groupName);
		if (test.isPresent()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Group already exists");
		}
		Group group = groupRepository.save(new Group(groupName));
		Optional<User> user = userRepository.findByEmail(String.valueOf(SecurityContextHolder.getContext().getAuthentication().getPrincipal()));
		if (user.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
		}
		group.addUser(user.get());
		groupRepository.save(group);
		return ResponseEntity.status(HttpStatus.OK).body("Successfully created");
	}

	@Transactional
	public List<Group> getGroupById(int id) {
		Optional<User> test = userRepository.findByEmail(String.valueOf(SecurityContextHolder.getContext().getAuthentication().getPrincipal()));
		if (test.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
		}
		User user = test.get();
		Optional<Group> group = groupRepository.findById(id);
		if (group.isPresent()) {
			if (user.getGroups().contains(group.get())) {
				user.setSelectedGroup(group.get());
				userRepository.save(user);
				return Collections.singletonList(group.get());
			} else {
				throw new ResponseStatusException(HttpStatus.FORBIDDEN, "User don't have access to this group");
			}
		} else {
			return Collections.emptyList();
		}
	}

	@Transactional
	public Group updateGroupById(int id, Group group) {
		Optional<Group> test = groupRepository.findById(id);
		if (test.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Group not found");
		}
		Group newGroup = new Group(group.getName());
		return groupRepository.save(newGroup);


	}
}
