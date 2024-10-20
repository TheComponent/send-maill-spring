package com.the_component.send_mail.controller;

import com.the_component.send_mail.service.SendMailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping({"/",""})
public class SendMailController {
    private final SendMailService sendMailService;

    @PostMapping("/sending/{email}")
    ResponseEntity<String> sendMai(@PathVariable("email")String email) {
        // input parameters which you want
        sendMailService.doSend(email, null, null, null, "HEHE", null, null);
        return ResponseEntity.status(HttpStatus.OK).body("Sent to your email");
    }
}
