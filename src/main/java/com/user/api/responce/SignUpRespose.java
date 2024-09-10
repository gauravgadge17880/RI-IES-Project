package com.user.api.responce;

import org.hibernate.annotations.Comment;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
public class SignUpRespose {
	
	private String msg;
	
	private String errorMsg;
	

}
