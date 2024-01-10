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
    String random = String.valueOf(Math.random() * 100000).substring(0, 5);

    public void sendMail(Mail mail) {        mail.setVerifyCode(random);

        SimpleMailMessage massage = new SimpleMailMessage();
        massage.setFrom("mohamedyasser2001898@gmail.com");
        massage.setSubject("Verify your account");
        massage.setTo(mail.getTo());
        massage.setText("your verify code is " + random);
        javaMailSender.send(massage);
    }


}
