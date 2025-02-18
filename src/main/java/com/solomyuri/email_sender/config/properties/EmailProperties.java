package com.solomyuri.email_sender.config.properties;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.boot.context.properties.ConfigurationProperties;

import com.solomyuri.email_sender.model.EmailAccount;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ConfigurationProperties(prefix = "email")
public class EmailProperties {

    private final Map<String, EmailAccount> emailAccounts;

    public EmailProperties(List<EmailAccount> accounts) {
	this.emailAccounts = accounts.stream().collect(Collectors.toMap(EmailAccount::getFrom, Function.identity()));
    }

}
