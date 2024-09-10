package com.user.api.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import jakarta.mail.internet.MimeMessage;

@Configuration
public class EmailUtils {

	@Autowired
	private JavaMailSender mailSender;
	
	public boolean sendMail(String to, String subject, String body) {
		
		boolean isSend = false;
		
		try {
			
			MimeMessage mimeMsg = mailSender.createMimeMessage();
			
			MimeMessageHelper helper = new MimeMessageHelper(mimeMsg);
			
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(body);
			
			mailSender.send(mimeMsg);
			
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return  isSend;
	}
}
