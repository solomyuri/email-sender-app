package com.solomyuri.email_sender.service;

import com.solomyuri.email_sender.model.MailRequest;

public interface EmailSender {

    void send(MailRequest request);
    
}
