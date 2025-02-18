package com.solomyuri.email_sender.model;

import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MailRequest {
    
    @Email(message = "from must be email format")
    @NotEmpty(message = "from must be not empty")
    private String from;
    
    @Email(message = "to must be email format")
    @NotEmpty(message = "to must be not empty")
    private String to;
    
    @NotEmpty(message = "subject must be not empty")
    private String subject;
    
    @NotNull(message = "content must be not null")
    private String content;
    
    private List<@Email(message = "cc must be email format") String> cc;
    private List<@Email(message = "bcc must be email format") String> bcc;
    
}
