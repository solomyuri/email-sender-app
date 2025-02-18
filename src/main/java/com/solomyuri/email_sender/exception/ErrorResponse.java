package com.solomyuri.email_sender.exception;

import lombok.Builder;

@Builder
public record ErrorResponse(int errorCode, String errorDescription) {

}
