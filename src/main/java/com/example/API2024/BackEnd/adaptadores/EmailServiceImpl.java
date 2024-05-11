package com.example.API2024.BackEnd.adaptadores;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.example.API2024.BackEnd.interfaces.EmailInterface;

@Service
public class EmailServiceImpl implements EmailInterface {
	
	@Value("${spring.mail.username}")
	private String fromEmail;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Override
	public String sendMail(String to, String subject, String body) {
		try {
			MimeMessage mimeMessage = javaMailSender.createMimeMessage();
			
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
			
			mimeMessageHelper.setFrom(fromEmail);
			mimeMessageHelper.setTo(to);
			mimeMessageHelper.setSubject(subject);
			mimeMessageHelper.setText(body);
			
			javaMailSender.send(mimeMessage);
			
			return "Email enviado com sucesso!";
		} catch (Exception e) {
			throw new RuntimeException("Erro ao enviar email: " + e.getMessage());
		}
	}
}
