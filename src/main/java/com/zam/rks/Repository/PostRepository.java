package com.zam.rks.Repository;

import com.zam.rks.model.Group;
import com.zam.rks.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

	Set<Post> findAllByGroupOrderByDateDesc(Group group);
}
