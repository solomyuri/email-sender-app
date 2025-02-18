package com.solomyuri.email_sender.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
@SuppressWarnings("serial")
public class ApplicationException extends RuntimeException {

    private final HttpStatus status;

    public ApplicationException(String message, HttpStatus status) {
	super(message);
	this.status = status;
    }

}
