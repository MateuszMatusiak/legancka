package com.zam.rks.Service;

import com.zam.rks.Dto.EventDto;
import com.zam.rks.Dto.GroupDto;
import com.zam.rks.Dto.Mapper.EventDtoMapper;
import com.zam.rks.Dto.Mapper.GroupDtoMapper;
import com.zam.rks.Repository.UserRepository;
import com.zam.rks.model.User;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Set;

@Service
@Scope
public class UserService {
	private final UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public Set<GroupDto> getGroupsForUser() {
		Optional<User> user = userRepository.findByEmail(String.valueOf(SecurityContextHolder.getContext().getAuthentication().getPrincipal()));
		if (user.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "User not found");
		}
		return GroupDtoMapper.mapGroupsToDto(user.get().getGroups());
	}

	@Transactional
	public User updateUser(User user) {
		Optional<User> test = userRepository.findByEmail(String.valueOf(SecurityContextHolder.getContext().getAuthentication().getPrincipal()));
		if (test.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "User not found");
		}
		if (user.getId() != test.get().getId() && user.getId() != 0) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "User don't have access to entity");
		}
		User newUser = new User(test.get(), user);
		return userRepository.save(newUser);
	}

	public Set<EventDto> getEventsForUser() {
		Optional<User> test = userRepository.findByEmail(String.valueOf(SecurityContextHolder.getContext().getAuthentication().getPrincipal()));
		if (test.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "User not found");
		}
		return EventDtoMapper.mapEventsToDto(test.get().getEvents());
	}
}
