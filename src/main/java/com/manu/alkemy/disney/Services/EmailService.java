package com.manu.alkemy.disney.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    JavaMailSender javaMailSender;

    public void sendMail(String toEmail, String subjet, String body){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("manu.20012009@gmail.com");
        message.setTo(toEmail);
        message.setSubject(subjet);
        message.setText(body);

        javaMailSender.send(message);
    }
}
