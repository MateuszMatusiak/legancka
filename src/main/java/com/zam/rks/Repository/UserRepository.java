package com.zam.rks.Repository;

import com.zam.rks.model.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	@EntityGraph(attributePaths = {"groups", "events"})
	Optional<User> findByEmail(String email);
}
