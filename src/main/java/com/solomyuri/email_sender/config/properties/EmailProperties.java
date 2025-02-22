package com.solomyuri.email_sender.config.properties;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

import com.solomyuri.email_sender.model.EmailAccount;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "email")
public class EmailProperties {

    private final List<EmailAccount> accounts;

}
