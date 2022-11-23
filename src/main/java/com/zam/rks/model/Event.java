package com.zam.rks.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.NonNull;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "m_event")
public class Event {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	@NonNull
	private Date startDate;
	private Date endDate;
	//TODO: w momencie kiedy mapa będzie w pełni ogarnięta to podpiąć to pod nią
	private String localization;
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "m_event_users",
			joinColumns = @JoinColumn(name = "event_id"),
			inverseJoinColumns = @JoinColumn(name = "user_id"))
	@JsonIgnore
	Set<User> users = new HashSet<>();


	public Event(Event e) {
		this.id = -1;
		this.name = e.name == null ? "" : e.name;
		this.startDate = e.startDate;
		this.endDate = e.endDate == null ? Date.valueOf(LocalDate.now()) : e.endDate;
		this.users = e.users == null ? new HashSet<>() : e.users;
		this.localization = e.localization == null ? "" : e.localization;
	}

	public Event() {
	}

	public void addUser(User user) {
		this.users.add(user);
	}
}
