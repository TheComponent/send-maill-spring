package com.the_component.send_mail.service;

public interface SendMailService {
    boolean saveEmail(String email);

    void sendMail(String destinationEmail, String subject,
                  String[] bcc, String[] cc, String content,
                  String fileName, String resource);
}
