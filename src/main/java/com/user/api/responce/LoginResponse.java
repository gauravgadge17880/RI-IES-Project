package com.user.api.responce;

import lombok.Data;

@Data
public class LoginResponse {
	
	private Integer userId;
	
	private String uname;
	
	private String userType;
	
	private boolean isValidLogin;
	
	private boolean pwdChanged;
	
	private DashboardResponse dashboardResponse;

}
