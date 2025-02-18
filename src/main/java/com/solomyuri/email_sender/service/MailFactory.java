package com.solomyuri.email_sender.service;

import org.springframework.mail.javamail.JavaMailSender;

public interface MailFactory {

    JavaMailSender getMailSender(String fromEmail);
}
