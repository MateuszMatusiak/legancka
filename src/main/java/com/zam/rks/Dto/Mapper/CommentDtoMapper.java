package com.zam.rks.Dto.Mapper;

import com.zam.rks.Dto.CommentDto;
import com.zam.rks.model.Comment;

import java.util.Set;
import java.util.stream.Collectors;

public class CommentDtoMapper {
	public static Set<CommentDto> mapCommentsToDto(Set<Comment> models) {
		return models.stream().map(CommentDtoMapper::mapToDto).collect(Collectors.toSet());
	}

	public static CommentDto mapToDto(Comment model) {
		return CommentDto.builder()
				.id(model.getId())
				.content(model.getContent())
				.date(model.getDate().toString())
				.user(UserDtoMapper.mapToDto(model.getUser()))
				.build();
	}
}
