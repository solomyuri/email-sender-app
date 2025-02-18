package com.solomyuri.email_sender.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.solomyuri.email_sender.model.MailRequest;
import com.solomyuri.email_sender.service.EmailSender;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("v1/smtp")
@RequiredArgsConstructor
public class EmailSenderController {

    private final EmailSender emailSender;
    
    @PostMapping
    public ResponseEntity<String> sendEmail(@Validated @RequestBody MailRequest request) {
	emailSender.send(request);
	return ResponseEntity.noContent().build();
    }
}
