package com.user.api.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.user.api.request.LoginRequest;
import com.user.api.request.PwdChangeReqeust;
import com.user.api.request.SignUpReqeust;
import com.user.api.responce.LoginResponse;
import com.user.api.responce.SignUpRespose;
import com.user.api.service.UserService;

import jakarta.annotation.PostConstruct;

@RestController
public class UserRestController {
	
	@Autowired
	private UserService service;

	
	// save user 
	@PostMapping("/user")
	public ResponseEntity<SignUpRespose> saveUser(@RequestBody SignUpReqeust request){
		
	SignUpRespose response = service.saveUser(request);	
		
	if(response.getMsg()!=null) {
		return new ResponseEntity<>(response, HttpStatus.OK);
	}else {
		return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	}
	
	//loging user
	@PostMapping("/login")
	public ResponseEntity<LoginResponse> userLogin(@RequestBody LoginRequest request){
		LoginResponse response = service.userLogin(request);
		return new ResponseEntity<>(response,HttpStatus.OK);
		
	}
	
	//change password
	@PostMapping("/pwd-change")
	public ResponseEntity<LoginResponse> updatePwd(@RequestBody PwdChangeReqeust request){
		LoginResponse login = service.updatePwd(request);
		return new ResponseEntity<> (login,HttpStatus.OK);
	}
	
	
	//Recover password
	@PostMapping("/recover-pws/{email}")
	public ResponseEntity<String> recoverPwd(@PathVariable String email){
		boolean isSend = service.recoverPwd(email);
		if(isSend) {
			return new ResponseEntity<>("Password sent to your email", HttpStatus.OK);
		}else {
			return new ResponseEntity<>("Invalid Email", HttpStatus.BAD_REQUEST);
			
		}
	}

	
	// greeting 
	 @GetMapping("/wlc")
	 public String welcome() {
		 return "welcome to IES - project";
	 }
}
