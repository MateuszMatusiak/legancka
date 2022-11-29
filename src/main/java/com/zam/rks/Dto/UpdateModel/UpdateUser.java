package com.zam.rks.Dto.UpdateModel;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Date;

@Getter
@AllArgsConstructor
public class UpdateUser {
	private String firstName;
	private String lastName;
	private String email;
	private Date birthdate;
	private String nickname;
	private String phoneNumber;

}
