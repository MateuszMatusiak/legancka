package com.zam.rks.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zam.rks.Dto.UserDto;
import lombok.Getter;
import lombok.Setter;
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

@Getter
@Setter
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
	private String nickname;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "selected_group", referencedColumnName = "id")
	private Group selectedGroup;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "m_group_users",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "group_id"))
	@JsonIgnore
	private Set<Group> groups = new HashSet<>();

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "m_event_users",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "event_id"))
	@JsonIgnore
	private Set<Event> events = new HashSet<>();
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

	public User(User oldUser, UserDto newUser, Group selectedGroup) {
		this.id = oldUser.id;
		this.email = oldUser.email;
		this.password = oldUser.password;

		this.firstName = newUser.getFirstName().isEmpty() ? oldUser.firstName : newUser.getFirstName();
		this.lastName = newUser.getLastName().isEmpty() ? oldUser.lastName : newUser.getLastName();
		this.birthdate = newUser.getBirthdate() == null ? oldUser.birthdate : newUser.getBirthdate();
		this.phoneNumber = newUser.getPhoneNumber().isEmpty() ? oldUser.phoneNumber : newUser.getPhoneNumber();
		this.nickname = newUser.getNickname().isEmpty() ? oldUser.nickname : newUser.getNickname();
		this.selectedGroup = selectedGroup;

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
		this.nickname = "";
		this.selectedGroup = null;
		this.role = UserRole.ROLE_USER;
		this.enabled = true;
		this.creationTime = new Timestamp(ZonedDateTime.now(ZoneId.of("Europe/Warsaw")).toInstant().toEpochMilli());
	}

	public User() {
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singleton(new SimpleGrantedAuthority(role.name()));
	}

	public String getRole() {
		return role.toString();
	}

	@Override
	public String getUsername() {
		return email;
	}

	public boolean isAccountNonExpired() {
		return true;
	}

	public boolean isAccountNonLocked() {
		return !locked;
	}

	public boolean isCredentialsNonExpired() {
		return true;
	}

	public boolean isEnabled() {
		return enabled;
	}
}
