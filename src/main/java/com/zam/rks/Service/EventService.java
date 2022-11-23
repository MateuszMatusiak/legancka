package com.zam.rks.Service;

import com.zam.rks.Dto.EventDto;
import com.zam.rks.Dto.Mapper.EventDtoMapper;
import com.zam.rks.Repository.EventRepository;
import com.zam.rks.Repository.UserRepository;
import com.zam.rks.model.Event;
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
public class EventService {
	private final EventRepository eventRepository;
	private final UserRepository userRepository;

	public EventService(EventRepository eventRepository, UserRepository userRepository) {
		this.eventRepository = eventRepository;
		this.userRepository = userRepository;
	}

	public Event createEvent(Event e) {
		Event newEvent = new Event(e);

		Optional<User> test = userRepository.findByEmail(String.valueOf(SecurityContextHolder.getContext().getAuthentication().getPrincipal()));
		if (test.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "User not found");
		}
		User user = test.get();
		newEvent.addUser(user);

		return eventRepository.save(newEvent);
	}

	@Transactional
	public Set<EventDto> getEventsForUser() {
		Optional<User> test = userRepository.findByEmail(String.valueOf(SecurityContextHolder.getContext().getAuthentication().getPrincipal()));
		if (test.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "User not found");
		}
		return EventDtoMapper.mapEventsToDto(test.get().getEvents());
	}
}
