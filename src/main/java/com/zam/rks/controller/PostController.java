package com.zam.rks.controller;

import com.zam.rks.Dto.CommentDto;
import com.zam.rks.Dto.PostDto;
import com.zam.rks.Service.PostService;
import com.zam.rks.model.UpdateModel.UpdateComment;
import com.zam.rks.model.UpdateModel.UpdatePost;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@AllArgsConstructor
@RestController
@RequestMapping("/post")
public class PostController {
	private final PostService postService;

	@GetMapping
	public Set<PostDto> getPosts() {
		return postService.getPosts();
	}

	@PutMapping
	public PostDto insertPost(@RequestBody UpdatePost post) {
		return postService.insertPost(post);
	}

	@PostMapping("/{id}")
	public PostDto updatePost(@PathVariable int id, @RequestBody UpdatePost post) {
		return postService.updatePost(id, post);
	}

	@GetMapping("/{id}/comments")
	public Set<CommentDto> getComments(@PathVariable int id) {
		return postService.getComments(id);
	}

	@PutMapping("/{id}/comment")
	public CommentDto insertComment(@PathVariable int id, @RequestBody UpdateComment comment) {
		return postService.insertComment(id, comment);
	}

	@PostMapping("/comment/{commentId}")
	public CommentDto updateComment(@PathVariable int commentId, @RequestBody UpdateComment comment) {
		return postService.updateComment(commentId, comment);
	}
}
