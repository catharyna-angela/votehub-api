package com.octalsystems.votehub.v1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class EmailService {

    private static final String CHARACTERS = "ACDEHJLMRSTV0123456789";
    private static final Random random = new Random();

    private final JavaMailSender mailSender;

    @Async
    public void sendCode(String recipient) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(recipient);
        message.setSubject("Código de ativação de conta");
        message.setText("Seu código de ativação é: " + generateCode());

        mailSender.send(message);
    }

    private String generateCode() {
        StringBuilder sb = new StringBuilder(6);

        for (int i = 0; i < 6; i++) {
            sb.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }

        return sb.toString();
    }

    //adicionar tempo de expiração do token
}
