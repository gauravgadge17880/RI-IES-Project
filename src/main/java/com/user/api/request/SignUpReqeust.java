package com.user.api.request;

import java.time.LocalDate;

import lombok.Data;
@Data
public class SignUpReqeust {

	private Integer userId;

	private String uname;

	private String email;

	private String pwd;

	private String gender;

	private LocalDate dob;

	private Long phno;

	private Long ssn;

	private String userType;

	private boolean pwdChanged;

}
