package com.the_component.send_mail.controller;

import com.the_component.send_mail.service.SendMailService;
import com.the_component.send_mail.utils.check.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sendMail")
public class SendMailController {
    private final SendMailService sendMailService;

    @PostMapping("/save/{email}")
    ResponseEntity<String> saveEmail(@PathVariable("email")String email) {
        if (!sendMailService.saveEmail(email)) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body("Saving failed");
        }

        return ResponseEntity.status(HttpStatus.OK).body("Saving successful");
    }

    @PostMapping("/sendMailWithCurrentTime/{email}")
    ResponseEntity<String> sendMailWithCurrentTime(@PathVariable("email")String email) {
        sendMailService.sendMailWithCurrentTime(email);
        return ResponseEntity.status(HttpStatus.OK).body("Sending successful");
    }
}
