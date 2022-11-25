package com.zam.rks.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "m_map")
public class MapModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private double latitude;
	private double longitude;
	private String color;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "event_id", referencedColumnName = "id")
	private Set<Event> events;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "group_id", referencedColumnName = "id")
	@JsonIgnore
	private Group group;

	public MapModel() {
	}

	public MapModel(String name, double latitude, double longitude, String color, Group group) {
		this.name = name;
		this.latitude = latitude;
		this.longitude = longitude;
		this.color = color;
		this.group = group;
	}
}
