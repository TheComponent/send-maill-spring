package com.the_component.send_mail.dto;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class Request {
    @Email(message = "this field must be email type")
    String email;
}
