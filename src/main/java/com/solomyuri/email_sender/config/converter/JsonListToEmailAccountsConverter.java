package com.solomyuri.email_sender.config.converter;

import java.io.IOException;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.solomyuri.email_sender.model.EmailAccount;

@Component
@ConfigurationPropertiesBinding
public class JsonListToEmailAccountsConverter implements Converter<String, List<EmailAccount>> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public List<EmailAccount> convert(String source) {
        try {
            return objectMapper.readValue(source, new TypeReference<List<EmailAccount>>() {});
        } catch (IOException e) {
            throw new IllegalArgumentException("Invalid JSON format for email accounts", e);
        }
    }
}