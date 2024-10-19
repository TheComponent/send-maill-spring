package com.the_component.send_mail.exception;

import lombok.Setter;

@Setter
public class InvalidInputParameter extends RuntimeException {
    private final String message;

    public InvalidInputParameter(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
