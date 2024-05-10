package com.example.API2024.BackEnd.interfaces;

public interface EmailInterface {
	String sendMail(String to, String[] cc, String subject, String body);
}
