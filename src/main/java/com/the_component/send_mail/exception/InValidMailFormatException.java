package com.the_component.send_mail.exception;

import lombok.Setter;

@Setter
public class InValidMailFormatException extends RuntimeException{
    private final String message;

    public InValidMailFormatException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
