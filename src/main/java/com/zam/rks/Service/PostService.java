package com.zam.rks.Service;

import com.zam.rks.Dto.Mapper.PostDtoMapper;
import com.zam.rks.Dto.PostDto;
import com.zam.rks.Repository.PostRepository;
import com.zam.rks.Repository.UserRepository;
import com.zam.rks.model.Post;
import com.zam.rks.model.UpdateModel.UpdatePost;
import com.zam.rks.model.User;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.Set;

@AllArgsConstructor
@Service
@Scope
public class PostService {

	private final PostRepository postRepository;
	private final UserRepository userRepository;

	public Set<PostDto> getPosts() {
		Optional<User> test = userRepository.findByEmail(String.valueOf(SecurityContextHolder.getContext().getAuthentication().getPrincipal()));
		if (test.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "User not found");
		}
		User user = test.get();

		Set<Post> posts = postRepository.findAllByGroup(user.getSelectedGroup());
		return PostDtoMapper.mapPostsToDto(posts);
	}

	public PostDto insertPost(UpdatePost post) {
		Optional<User> test = userRepository.findByEmail(String.valueOf(SecurityContextHolder.getContext().getAuthentication().getPrincipal()));
		if (test.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "User not found");
		}
		User user = test.get();

		Post postToSave = new Post(post.getContent(), user);
		return PostDtoMapper.mapToDto(postRepository.save(postToSave));
	}

	public PostDto updatePost(int id, UpdatePost post) {
		Optional<User> test = userRepository.findByEmail(String.valueOf(SecurityContextHolder.getContext().getAuthentication().getPrincipal()));
		if (test.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "User not found");
		}
		User user = test.get();

		Optional<Post> postToUpdate = postRepository.findById(id);
		if (postToUpdate.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found");
		}
		if (!postToUpdate.get().getUser().equals(user)) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "User not allowed to update this post");
		}
		postToUpdate.get().setContent(post.getContent());
		return PostDtoMapper.mapToDto(postRepository.save(postToUpdate.get()));
	}
}
