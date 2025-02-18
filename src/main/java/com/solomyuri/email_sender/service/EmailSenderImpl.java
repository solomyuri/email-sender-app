package com.solomyuri.email_sender.service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.solomyuri.email_sender.exception.ApplicationException;
import com.solomyuri.email_sender.model.MailRequest;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailSenderImpl implements EmailSender {

    private final MailFactory mailFactory;

    public void send(MailRequest request) {
	try {
	    JavaMailSender mailSender = mailFactory.getMailSender(request.getFrom());
	    MimeMessage message = mailSender.createMimeMessage();
	    MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

	    helper.setFrom(request.getFrom());
	    helper.setTo(request.getTo());
	    helper.setSubject(request.getSubject());
	    helper.setText(new String(Base64.getDecoder().decode(request.getContent()), StandardCharsets.UTF_8), true);
	    
	    if(Objects.nonNull(request.getCc()) && !request.getCc().isEmpty())
		helper.setCc(request.getCc().toArray(new String[0]));
	    
	    if(Objects.nonNull(request.getBcc()) && !request.getBcc().isEmpty())
		helper.setBcc(request.getBcc().toArray(new String[0]));
	    
	    mailSender.send(message);
	    log.info("Email sent from {} to {}", request.getFrom(), request.getTo());

	} catch (MessagingException e) {
	    log.error(e.getMessage());
	    throw new ApplicationException("Failed to send email", HttpStatus.INTERNAL_SERVER_ERROR);
	}
    }

}
