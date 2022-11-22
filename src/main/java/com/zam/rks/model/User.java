package com.zam.rks.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "m_user")
public class User implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String email;
	@JsonIgnore
	private String password;
	private String firstName;
	private String lastName;
	private Date birthdate;
	private String phoneNumber;
	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "selected_group", referencedColumnName = "id")
	private Group selectedGroup;

	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "m_group_users",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "group_id"))
	private Set<Group> groups = new HashSet<>();
	@JsonIgnore
	@Column(columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP")
	private Timestamp creationTime;
	@JsonIgnore
	private Boolean enabled = false;
	@JsonIgnore
	private final Boolean locked = false;
	@Enumerated(EnumType.STRING)
	@JsonIgnore
	private UserRole role;

	public User(User oldUser, User newUser) {
		this.id = oldUser.id;
		this.email = oldUser.email;
		this.password = oldUser.password;

		this.firstName = newUser.firstName.isEmpty() ? oldUser.firstName : newUser.firstName;
		this.lastName = newUser.lastName.isEmpty() ? oldUser.lastName : newUser.lastName;
		this.birthdate = newUser.birthdate == null ? oldUser.birthdate : newUser.birthdate;
		this.phoneNumber = newUser.phoneNumber.isEmpty() ? oldUser.phoneNumber : newUser.phoneNumber;

		this.selectedGroup = oldUser.selectedGroup;
		this.role = oldUser.role;
		this.enabled = oldUser.enabled;
		this.creationTime = oldUser.creationTime;
	}


	public User(String email, String password) {
		this.email = email;
		this.password = password;
		this.firstName = "";
		this.lastName = "";
		this.birthdate = null;
		this.phoneNumber = "";
		this.selectedGroup = null;
		this.role = UserRole.ROLE_USER;
		this.enabled = true;
		this.creationTime = new Timestamp(ZonedDateTime.now(ZoneId.of("Europe/Warsaw")).toInstant().toEpochMilli());
	}

	public User() {
	}

	public int getId() {
		return id;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singleton(new SimpleGrantedAuthority(role.name()));

	}

	public Group getSelectedGroup() {
		return selectedGroup;
	}

	public void setSelectedGroup(Group selectedGroup) {
		this.selectedGroup = selectedGroup;
	}

	public String getPassword() {
		return password;
	}

	public String getUsername() {
		return email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Timestamp getCreationTime() {
		return creationTime;
	}

	public boolean isAccountNonExpired() {
		return true;
	}

	public boolean isAccountNonLocked() {
		return true;
	}

	public boolean isCredentialsNonExpired() {
		return true;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	public Set<Group> getGroups() {
		return groups;
	}

	public void setGroups(Set<Group> groups) {
		this.groups = groups;
	}
}
