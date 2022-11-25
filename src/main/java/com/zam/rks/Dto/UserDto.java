package com.zam.rks.Dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zam.rks.model.Group;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;
import java.sql.Date;

@Getter
@Builder
public class UserDto implements Serializable {
	private int id;
	private String email;
	private String firstName;
	private String lastName;
	private Date birthdate;
	private String phoneNumber;
	private String nickname;
	private String role;
	private GroupDto selectedGroup;
}
