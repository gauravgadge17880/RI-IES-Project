package com.user.api.request;

import lombok.Data;

@Data
public class LoginRequest {
	
	private String pwd;
	
	private String email;

}
