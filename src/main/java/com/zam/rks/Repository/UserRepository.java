package com.zam.rks.Repository;

import com.zam.rks.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findByUsername(String username);
}
