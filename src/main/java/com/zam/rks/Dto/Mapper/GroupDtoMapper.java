package com.zam.rks.Dto.Mapper;

import com.zam.rks.Dto.GroupDto;
import com.zam.rks.model.Group;

import java.util.Set;
import java.util.stream.Collectors;

public class GroupDtoMapper {

	private GroupDtoMapper() {
	}

	public static Set<GroupDto> mapGroupsToDto(Set<Group> groups) {
		return groups.stream().map(GroupDtoMapper::mapToDto).collect(Collectors.toSet());
	}

	public static GroupDto mapToDto(Group group) {
		return GroupDto.builder()
				.id(group.getId())
				.name(group.getName())
				.build();
	}
}
