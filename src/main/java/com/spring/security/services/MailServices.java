package com.spring.security.services;

import com.spring.security.dto.Mail;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailServices {


    private final JavaMailSender javaMailSender;

    public void sendMail(Mail mail) {
        SimpleMailMessage massage = new SimpleMailMessage();
        massage.setFrom("mohamedyasser2001898@gmail.com");
        massage.setSubject(mail.getSubject());
        massage.setTo(mail.getTo());
        massage.setText(mail.getText());
        javaMailSender.send(massage);
    }

}
