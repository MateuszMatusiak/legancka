package com.zam.rks.controller;

import com.zam.rks.Service.EventService;
import com.zam.rks.model.Event;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventController {

	private final EventService eventService;

	public EventController(EventService eventService) {
		this.eventService = eventService;
	}

	@PutMapping("/event")
	public Event createEvent(@RequestBody Event event) {
		return eventService.createEvent(event);
	}

}
