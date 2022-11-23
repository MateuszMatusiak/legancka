package com.zam.rks.controller;

import com.zam.rks.Dto.EventDto;
import com.zam.rks.Service.EventService;
import com.zam.rks.model.Event;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class EventController {

	private EventService eventService;

	public EventController(EventService eventService) {
		this.eventService = eventService;
	}

	@PutMapping("/event")
	public Event createEvent(@RequestBody Event event) {
		return eventService.createEvent(event);
	}

	@GetMapping("/events")
	public Set<EventDto> getEventsForUser() {
		return eventService.getEventsForUser();
	}

}
