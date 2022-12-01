package com.zam.rks.Service;

import com.zam.rks.Dto.CommentDto;
import com.zam.rks.Dto.Mapper.CommentDtoMapper;
import com.zam.rks.Dto.Mapper.PostDtoMapper;
import com.zam.rks.Dto.PostDto;
import com.zam.rks.Repository.CommentRepository;
import com.zam.rks.Repository.PostRepository;
import com.zam.rks.Repository.UserRepository;
import com.zam.rks.model.Comment;
import com.zam.rks.model.Post;
import com.zam.rks.model.UpdateModel.UpdateComment;
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
	private final CommentRepository commentRepository;

	public Set<PostDto> getPosts() {
		Optional<User> test = userRepository.findByEmail(String.valueOf(SecurityContextHolder.getContext().getAuthentication().getPrincipal()));
		if (test.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "User not found");
		}
		User user = test.get();

		Set<Post> posts = postRepository.findAllByGroupOrderByDateDesc(user.getSelectedGroup());
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
		if (postToUpdate.get().getUser().getId() != user.getId()) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "User not allowed to update this post");
		}
		postToUpdate.get().setContent(post.getContent());
		return PostDtoMapper.mapToDto(postRepository.save(postToUpdate.get()));
	}

	public Set<CommentDto> getComments(int id) {
		Optional<User> test = userRepository.findByEmail(String.valueOf(SecurityContextHolder.getContext().getAuthentication().getPrincipal()));
		if (test.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "User not found");
		}
		User user = test.get();

		Set<Comment> comments = commentRepository.findAllByPostIdOrderByDateDesc(id);
		return CommentDtoMapper.mapCommentsToDto(comments);
	}

	public CommentDto insertComment(int id, UpdateComment comment) {
		Optional<User> test = userRepository.findByEmail(String.valueOf(SecurityContextHolder.getContext().getAuthentication().getPrincipal()));
		if (test.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "User not found");
		}
		User user = test.get();

		Optional<Post> post = postRepository.findById(id);
		if (post.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found");
		}
		Comment commentToSave = new Comment(comment.getContent(), post.get(), user);
		Comment savedComment = commentRepository.save(commentToSave);
		return CommentDtoMapper.mapToDto(savedComment);
	}

	public CommentDto updateComment(int commentId, UpdateComment comment) {
		Optional<User> test = userRepository.findByEmail(String.valueOf(SecurityContextHolder.getContext().getAuthentication().getPrincipal()));
		if (test.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "User not found");
		}
		User user = test.get();

		Optional<Comment> commentToUpdate = commentRepository.findById(commentId);
		if (commentToUpdate.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Comment not found");
		}
		if (commentToUpdate.get().getUser().getId() != user.getId()) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "User not allowed to update this comment");
		}
		commentToUpdate.get().setContent(comment.getContent());
		return CommentDtoMapper.mapToDto(commentRepository.save(commentToUpdate.get()));
	}
}
