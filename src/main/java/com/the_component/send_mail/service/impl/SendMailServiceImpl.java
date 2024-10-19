package com.the_component.send_mail.service.impl;

import com.the_component.send_mail.exception.FailedSendEmailException;
import com.the_component.send_mail.exception.InValidMailFormatException;
import com.the_component.send_mail.exception.InvalidInputParameter;
import com.the_component.send_mail.model.StorageCode;
import com.the_component.send_mail.repository.SendMailRepository;
import com.the_component.send_mail.service.SendMailService;
import com.the_component.send_mail.utils.check.Email;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SendMailServiceImpl implements SendMailService {
    private final SendMailRepository sendMailRepository;
    private final JavaMailSender javaMailSender;

    ThreadPoolExecutor executor;

    @Override
    @Transactional
    public boolean saveEmail(String email) {
        if (!Email.isEmail(email)) {
            throw new InValidMailFormatException(email + "is not a email");
        }
        StorageCode storageCode = new StorageCode();
        storageCode.setEmail(email);
        return sendMailRepository.save(storageCode).getId() != null;
    }

    @Override
    public void sendMail(String sourceEmail, String destinationEmail, String subject,
                         String[] bcc, String[] cc, String content,
                         String fileName, String resource) {
        executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
        executor.submit(() -> {
            try {
                javaMailSender.send(mimeMessage -> {
                    MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");

                    if (!Email.isEmail(sourceEmail)) {
                        throw new InvalidInputParameter(sourceEmail + " is not a valid email!");
                    }
                    message.setFrom(sourceEmail);

                    if (!Email.isEmail(destinationEmail)) {
                        throw new InvalidInputParameter(destinationEmail + " is not a valid email!");
                    }
                    message.setTo(destinationEmail);

                    message.setSubject(subject);
                    message.setBcc(bcc);
                    message.setCc(cc);
                    message.setText(content);

                    try {
                        if (fileName != null && resource != null) {
                            message.addAttachment(fileName, new ClassPathResource(resource));
                        }
                    } catch (RuntimeException e) {
                        throw new InvalidInputParameter("Error while attaching file!");
                    }
                });
            } catch (Exception e) {
                throw new FailedSendEmailException("Error while sending email: " + e.getMessage());
            }
        });

    }

}
