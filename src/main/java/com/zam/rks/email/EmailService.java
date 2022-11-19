package com.zam.rks.email;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService {

	private final static Logger LOGGER = LoggerFactory
			.getLogger(EmailService.class);

	private final JavaMailSender mailSender;

	public EmailService(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	@Async
	public void send(String to, String email) {
		try {
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper helper =
					new MimeMessageHelper(mimeMessage, "utf-8");
			helper.setText(email, true);
			helper.setTo(to);
			helper.setSubject("Potwierdzenie email'a");
			helper.setFrom("zam@rks.com");
			mailSender.send(mimeMessage);
		} catch (MessagingException e) {
			LOGGER.error("Nie udało się wysłać wiadomości", e);
			throw new IllegalStateException("Nie udało się wysłać wiadomości");
		}
	}
}
