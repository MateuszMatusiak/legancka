package com.zam.rks.Service;

import com.zam.rks.Dto.EventDto;
import com.zam.rks.Dto.GroupDto;
import com.zam.rks.Dto.Mapper.EventDtoMapper;
import com.zam.rks.Dto.Mapper.GroupDtoMapper;
import com.zam.rks.Dto.Mapper.UserDtoMapper;
import com.zam.rks.Dto.UserDto;
import com.zam.rks.Repository.GroupRepository;
import com.zam.rks.Repository.UserRepository;
import com.zam.rks.model.Group;
import com.zam.rks.model.UpdateModel.UpdateUser;
import com.zam.rks.model.User;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Set;

@AllArgsConstructor
@Service
@Scope
public class UserService {
	private final UserRepository userRepository;
	private final GroupRepository groupRepository;

	public Set<GroupDto> getGroupsForUser() {
		Optional<User> user = userRepository.findByEmail(String.valueOf(SecurityContextHolder.getContext().getAuthentication().getPrincipal()));
		if (user.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "User not found");
		}
		return GroupDtoMapper.mapGroupsToDto(user.get().getGroups());
	}

	@Transactional
	public UserDto updateUser(UpdateUser user) {
		Optional<User> test = userRepository.findByEmail(String.valueOf(SecurityContextHolder.getContext().getAuthentication().getPrincipal()));
		if (test.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "User not found");
		}
		User newUser = new User(test.get(), user);
		User u = userRepository.save(newUser);
		return UserDtoMapper.mapToDto(u);
	}

	public UserDto getUser() {
		Optional<User> test = userRepository.findByEmail(String.valueOf(SecurityContextHolder.getContext().getAuthentication().getPrincipal()));
		if (test.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
		}
		return UserDtoMapper.mapToDto(test.get());
	}

	public Set<EventDto> getEventsForUser() {
		Optional<User> test = userRepository.findByEmail(String.valueOf(SecurityContextHolder.getContext().getAuthentication().getPrincipal()));
		if (test.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "User not found");
		}
		return EventDtoMapper.mapEventsToDto(test.get().getEvents());
	}

	@Transactional
	public GroupDto setGroupById(int id) {
		Optional<User> test = userRepository.findByEmail(String.valueOf(SecurityContextHolder.getContext().getAuthentication().getPrincipal()));
		if (test.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "User not found");
		}
		User user = test.get();
		Optional<Group> group = groupRepository.findById(id);
		if (group.isPresent()) {
			user.setSelectedGroup(group.get());
			userRepository.save(user);
			return GroupDtoMapper.mapToDto(group.get());
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Group not found");
		}
	}
}
