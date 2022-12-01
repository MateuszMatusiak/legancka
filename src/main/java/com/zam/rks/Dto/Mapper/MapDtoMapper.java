package com.zam.rks.Dto.Mapper;

import com.zam.rks.Dto.MapDto;
import com.zam.rks.model.MapModel;

import java.util.Set;
import java.util.stream.Collectors;

public class MapDtoMapper {

	public static Set<MapDto> mapMapModelToDto(Set<MapModel> models) {
		return models.stream().map(MapDtoMapper::mapToDto).collect(Collectors.toSet());
	}

	public static MapDto mapToDto(MapModel model) {
		return MapDto.builder()
				.name(model.getName())
				.latitude(model.getLatitude())
				.longitude(model.getLongitude())
				.color(model.getColor())
				.build();
	}
}
