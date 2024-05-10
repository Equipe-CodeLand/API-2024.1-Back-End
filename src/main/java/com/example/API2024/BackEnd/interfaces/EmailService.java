package com.example.API2024.BackEnd.interfaces;

import org.springframework.web.multipart.MultipartFile;

public interface EmailService {
	String sendMail(String to, String[] cc, String subject, String body);
}
