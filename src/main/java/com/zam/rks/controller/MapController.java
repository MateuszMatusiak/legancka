package com.zam.rks.controller;

import com.zam.rks.Dto.MapDto;
import com.zam.rks.Service.MapService;
import com.zam.rks.model.MapModel;
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
@RequestMapping("/map")
public class MapController {
	private final MapService mapService;

	@GetMapping("/point/{id}")
	public MapModel getMapPointById(@PathVariable int id) {
		return mapService.getMapPointById(id);
	}

	@GetMapping
	public Set<MapModel> getMap() {
		return mapService.getMap();
	}

	@PutMapping("/point")
	public MapModel insertMapPoint(@RequestBody MapDto mapModel) {
		return mapService.insertMapPoint(mapModel);
	}

	@PostMapping("/point{id}")
	public MapModel updateMapPoint(@PathVariable int id,
								   @RequestBody MapDto mapModel) {
		return mapService.updateMapPoint(id, mapModel);
	}
}
