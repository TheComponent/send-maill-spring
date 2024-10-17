package com.the_component.send_mail.service;

public interface SendMailService {
    boolean saveEmail(String email);

    boolean sendMailWithCurrentTime(String email);

    boolean sendMailWithString(String email, String content);

    boolean isExpireCode(String email);


}
