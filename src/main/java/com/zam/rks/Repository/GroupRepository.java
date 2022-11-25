package com.zam.rks.Repository;

import com.zam.rks.model.Group;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<Group, Integer> {

	@EntityGraph(attributePaths = {"users"})
	Optional<Group> findByName(String groupName);

	@EntityGraph(attributePaths = {"users"})
	List<Group> findAll();

	@EntityGraph(attributePaths = {"users"})
	Optional<Group> findById(int id);

}
