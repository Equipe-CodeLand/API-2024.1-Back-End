package com.example.API2024.BackEnd.controller;

import com.example.API2024.BackEnd.interfaces.EmailService;
import com.example.API2024.BackEnd.service.EmailSendRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class EmailSendController {
	
	private final EmailService emailService;
	
	public EmailSendController(EmailService emailService) {
		this.emailService = emailService;
	}
	
	@PostMapping("/email/send")
	public String send(@RequestBody EmailSendRequest emailRequest){
		String to = emailRequest.getTo();
		String[] cc = emailRequest.getCc();
		String subject = emailRequest.getSubject();
		String body = emailRequest.getBody();
		return emailService.sendMail(to, cc, subject, body);
	}
}
