package com.user.api.request;

import lombok.Data;

@Data
public class SignInRequest {
	
	private Integer userId;
	
	private String email;
	
	private String pwd;
	
	private String confirmedpwd;
	
	
	

}
