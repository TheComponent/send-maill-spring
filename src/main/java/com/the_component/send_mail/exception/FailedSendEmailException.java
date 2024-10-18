package com.the_component.send_mail.exception;

import lombok.Setter;

@Setter
public class FailedSendEmailException extends RuntimeException {
    private final String message;

    public FailedSendEmailException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
