package com.the_component.send_mail.service.impl;

import com.the_component.send_mail.model.StorageCode;
import com.the_component.send_mail.repository.SendMailRepository;
import com.the_component.send_mail.service.SendMailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SendMailServiceImpl implements SendMailService {
    private final SendMailRepository sendMailRepository;

    @Override
    @Transactional
    public boolean saveEmail(String email) {
        StorageCode storageCode = new StorageCode();
        storageCode.setEmail(email);
        return sendMailRepository.save(storageCode).getId() != null;
    }

    @Override
    public boolean sendMailWithCurrentTime(String email) {
        return false;
    }

    @Override
    public boolean sendMailWithString(String email, String content) {
        return false;
    }

    @Override
    public boolean isExpireCode(String email) {
        return false;
    }
}
