package com.zam.rks.Dto.Mapper;

import com.zam.rks.Dto.PostDto;
import com.zam.rks.model.Post;

import java.util.Set;
import java.util.stream.Collectors;

public class PostDtoMapper {
	public static Set<PostDto> mapPostsToDto(Set<Post> models) {
		return models.stream().map(PostDtoMapper::mapToDto).collect(Collectors.toSet());
	}

	public static PostDto mapToDto(Post model) {
		return PostDto.builder()
				.id(model.getId())
				.content(model.getContent())
				.date(model.getDate().toString())
				.user(UserDtoMapper.mapToDto(model.getUser()))
				.build();
	}
}
