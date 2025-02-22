package com.solomyuri.email_sender.service;

import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import com.solomyuri.email_sender.config.properties.EmailProperties;
import com.solomyuri.email_sender.model.EmailAccount;

@Service
@EnableConfigurationProperties(EmailProperties.class)
public class MailFactoryImpl implements MailFactory {
    
    private final Map<String, JavaMailSender> mailSenderCache;
    
    public MailFactoryImpl(EmailProperties properties) {
	mailSenderCache = properties.getAccounts().stream()
	        .collect(Collectors.toMap(EmailAccount::getFrom, this::createMailSender));

	if (mailSenderCache.isEmpty())
	    throw new IllegalStateException("No SMTP config found in application properties");
    }
    
    @Override
    public JavaMailSender getMailSender(String fromEmail) {

	JavaMailSender sender = mailSenderCache.get(fromEmail);

	if (sender == null) {
	    throw new IllegalArgumentException("No SMTP config for email: " + fromEmail);
	}

	return sender;
    }
    
    private JavaMailSender createMailSender(EmailAccount accountConfig) {
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
