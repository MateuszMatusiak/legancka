package com.zam.rks.Repository;

import com.zam.rks.model.Group;
import com.zam.rks.model.MapModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface MapRepository extends JpaRepository<MapModel, Integer> {
	Set<MapModel> findAllByGroup(Group group);
}
