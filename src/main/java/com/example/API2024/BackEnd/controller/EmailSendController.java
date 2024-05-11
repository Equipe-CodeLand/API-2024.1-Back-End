package com.example.API2024.BackEnd.controller;

import com.example.API2024.BackEnd.interfaces.EmailInterface;
import com.example.API2024.BackEnd.service.EmailSendRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailSendController {
	
	private final EmailInterface emailInterface;
	
	public EmailSendController(EmailInterface emailInterface) {
		this.emailInterface = emailInterface;
	}
	
	@PostMapping("/email/send")
	public String send(@RequestBody EmailSendRequest emailRequest){
		String to = emailRequest.getTo();
		String subject = emailRequest.getSubject();
		String body = emailRequest.getBody();
		return emailInterface.sendMail(to, subject, body);
	}
}
