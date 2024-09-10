package com.user.api.enity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="USER_INFO")
public class UserInfoEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
