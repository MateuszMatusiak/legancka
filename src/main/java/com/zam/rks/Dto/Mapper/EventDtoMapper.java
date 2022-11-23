package com.zam.rks.Dto.Mapper;

import com.zam.rks.Dto.EventDto;
import com.zam.rks.model.Event;

import java.util.Set;
import java.util.stream.Collectors;

public class EventDtoMapper {

	private EventDtoMapper() {
	}

	public static Set<EventDto> mapEventsToDto(Set<Event> events) {
		return events.stream().map(EventDtoMapper::mapToDto).collect(Collectors.toSet());
	}

	private static EventDto mapToDto(Event event) {
		return EventDto.builder()
				.id(event.getId())
				.name(event.getName())
				.startDate(event.getStartDate())
				.endDate(event.getEndDate())
				.localization(event.getLocalization())
				.build();
	}
}
