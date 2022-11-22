package com.zam.rks.model;

import org.springframework.lang.NonNull;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

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

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "users", referencedColumnName = "id")
	Set<User> users = new HashSet<>();

	//TODO: w momencie kiedy mapa będzie w pełni ogarnięta to podpiąć to pod nią
	private String localization;

	public Event(String name, @NonNull Date startDate, Date endDate, Set<User> users, String localization) {
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.users = users;
		this.localization = localization;
	}

	public Event() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@NonNull
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(@NonNull Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public String getLocalization() {
		return localization;
	}

	public void setLocalization(String localization) {
		this.localization = localization;
	}
}
