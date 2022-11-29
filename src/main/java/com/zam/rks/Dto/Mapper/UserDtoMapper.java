package com.zam.rks.Dto.Mapper;

import com.zam.rks.Dto.UserDto;
import com.zam.rks.model.User;

import java.util.Set;
import java.util.stream.Collectors;

public class UserDtoMapper {

	public static Set<UserDto> mapUsersToDto(Set<User> users) {
		return users.stream().map(UserDtoMapper::mapToDto).collect(Collectors.toSet());
	}

	public static UserDto mapToDto(User user) {
		return UserDto.builder()
				.id(user.getId())
				.email(user.getEmail())
				.firstName(user.getFirstName())
				.lastName(user.getLastName())
				.birthdate(user.getBirthdate())
				.phoneNumber(user.getPhoneNumber())
				.nickname(user.getNickname())
				.role(user.getRole())
				.selectedGroupId(user.getSelectedGroup().getId())
				.build();
	}
}
