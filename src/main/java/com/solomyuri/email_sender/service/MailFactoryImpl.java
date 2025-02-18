package com.solomyuri.email_sender.service;

import java.util.Map;
import java.util.Properties;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import com.solomyuri.email_sender.config.properties.EmailProperties;
import com.solomyuri.email_sender.model.EmailAccount;

@Service
@EnableConfigurationProperties(EmailProperties.class)
public class MailFactoryImpl implements MailFactory {
    
    private final Map<String, EmailAccount> mailAccounts;
    
    public MailFactoryImpl(EmailProperties properties) {
	this.mailAccounts = properties.getEmailAccounts();
    }
    
    @Override
    public JavaMailSender getMailSender(String fromEmail) {
	
        EmailAccount accountConfig = mailAccounts.get(fromEmail);
        
        if (accountConfig == null) {
            throw new IllegalArgumentException("No SMTP config for email: " + fromEmail);
        }

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(accountConfig.getHost());
        mailSender.setPort(accountConfig.getPort());
        mailSender.setUsername(accountConfig.getUsername());
        mailSender.setPassword(accountConfig.getPassword());

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        return mailSender;
    }

}
