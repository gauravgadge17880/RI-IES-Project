package com.user.api.service;

import com.user.api.request.LoginRequest;
import com.user.api.request.PwdChangeReqeust;
import com.user.api.request.SignUpReqeust;
import com.user.api.responce.LoginResponse;
import com.user.api.responce.SignUpRespose;

public interface UserService {
	
	public SignUpRespose saveUser(SignUpReqeust request);
	
	public LoginResponse userLogin(LoginRequest request);
	
	public LoginResponse updatePwd(PwdChangeReqeust request);
	
	public boolean recoverPwd(String mail);
	

}
