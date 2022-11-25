package com.zam.rks.Service;

import com.zam.rks.Dto.MapDto;
import com.zam.rks.Repository.MapRepository;
import com.zam.rks.Repository.UserRepository;
import com.zam.rks.model.MapModel;
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
public class MapService {
	private final MapRepository mapRepository;
	private final UserRepository userRepository;

	public MapModel getMapPointById(int id) {
		return mapRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.FORBIDDEN, "Point not found"));
	}

	public Set<MapModel> getMap() {
		Optional<User> test = userRepository.findByEmail(String.valueOf(SecurityContextHolder.getContext().getAuthentication().getPrincipal()));
		if (test.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "User not found");
		}
		User user = test.get();
		return mapRepository.findAllByGroup(user.getSelectedGroup());
	}

	public MapModel insertMapPoint(MapDto mapModel) {
		Optional<User> test = userRepository.findByEmail(String.valueOf(SecurityContextHolder.getContext().getAuthentication().getPrincipal()));
		if (test.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "User not found");
		}
		User user = test.get();
		MapModel newPoint = new MapModel(mapModel.getName(), mapModel.getLatitude(), mapModel.getLongitude(), mapModel.getColor(), user.getSelectedGroup());
		return mapRepository.save(newPoint);
	}

	public MapModel updateMapPoint(int id, MapDto mapModel) {
		MapModel point = mapRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Point not found"));
		point.setName(mapModel.getName());
		point.setLatitude(mapModel.getLatitude());
		point.setLongitude(mapModel.getLongitude());
		point.setColor(mapModel.getColor());
		return mapRepository.save(point);
	}
}
