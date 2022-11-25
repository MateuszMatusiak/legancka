package com.zam.rks.Service;

import com.zam.rks.Dto.Mapper.UserDtoMapper;
import com.zam.rks.Dto.UserDto;
import com.zam.rks.Repository.GroupRepository;
import com.zam.rks.Repository.UserRepository;
import com.zam.rks.model.Group;
import com.zam.rks.model.User;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Set;

@AllArgsConstructor
@Service
@Scope
public class GroupService {

	private final GroupRepository groupRepository;
	private final UserRepository userRepository;

	@Transactional
	public ResponseEntity<String> createNewGroup(String groupName) {
		Optional<Group> test = groupRepository.findByName(groupName);
		if (test.isPresent()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Group already exists");
		}
		Group group = groupRepository.save(new Group(groupName));
		Optional<User> user = userRepository.findByEmail(String.valueOf(SecurityContextHolder.getContext().getAuthentication().getPrincipal()));
		if (user.isEmpty()) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User not found");
		}
		group.addUser(user.get());
		groupRepository.save(group);
		return ResponseEntity.status(HttpStatus.OK).body("Successfully created");
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

	public Set<UserDto> getUsersForGroup(int id) {
		Optional<User> test = userRepository.findByEmail(String.valueOf(SecurityContextHolder.getContext().getAuthentication().getPrincipal()));
		if (test.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "User not found");
		}
		Optional<Group> group = groupRepository.findById(id);
		if(group.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Group not found");
		}

		return UserDtoMapper.mapUsersToDto(group.get().getUsers());
	}
}
