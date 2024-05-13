package com.example.API2024.BackEnd.service;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class EmailSendRequest {
	private String to;
	private String[] cc;
	private String subject;
	private String body;
}
