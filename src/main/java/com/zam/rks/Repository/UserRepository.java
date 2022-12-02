package com.zam.rks.Repository;

import com.zam.rks.model.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	@EntityGraph(attributePaths = {"groups", "events"})
	@Query("SELECT u FROM User u JOIN FETCH u.groups JOIN FETCH u.events WHERE u.email = ?1 ")
	Optional<User> findByEmail(String email);
}
