package com.mugja.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class MailService {

	@Autowired
	private JavaMailSender javaMailSender;
	@Autowired
	private TemplateEngine templateEngine;


	//심플메일
	public void sendSimpleEmail(String email) {
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setSubject("여기묵자 인증번호 메일");
		msg.setTo(email);
		msg.setText("심플내용");

		javaMailSender.send(msg);
	}

	//HTML메일
	@Async
	public void sendHTMLEmail(String email,String number) {
		MimeMessage msg = javaMailSender.createMimeMessage();


		try {
			msg.setSubject("여기묵자 인증번호 메일");
			msg.setText(setContext(number), "UTF-8", "html");
			msg.addRecipients(MimeMessage.RecipientType.TO, email);
			javaMailSender.send(msg);
		} catch (MessagingException e) {
			e.printStackTrace();
		}


	}



	//랜덤문자생성
	public String createnumber() {
		String number="";
		String[] charSet = new String[] {
				"0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
				"a", "b", "c", "d", "e", "f", "g", "h", "i", "j",
				"k", "l", "m", "n", "o", "p", "q", "r", "s", "t",
				"u", "v", "w", "x", "y", "z",
				"A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
				"K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
				"U", "V", "W", "X", "Y", "Z"
		};
		for(int i=0;i<6;i++) {
			int randIndex = (int)(Math.random() * charSet.length);
			number += charSet[randIndex];
		}
		return number;
	}


	//html 페이지 저장
	public String setContext(String number) {
		Context context = new Context();
		context.setVariable("number", number);
		return templateEngine.process("view/mail", context);
	}
}

