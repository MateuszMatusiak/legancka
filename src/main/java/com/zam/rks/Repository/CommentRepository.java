package com.zam.rks.Repository;

import com.zam.rks.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
	Set<Comment> findAllByPostIdOrderByDateDesc(int id);
}
