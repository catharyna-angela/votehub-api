package com.octal.votehub.api.v1.service;

import com.octal.votehub.api.v1.entity.Code;
import com.octal.votehub.api.v1.repository.CodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CodeService {

    private static final String CHARACTERS = "ACDEHJLMRSTV0123456789";
    private static final long EXPIRATION_SECONDS = 600L; //10 minutos
    private static final SecureRandom random = new SecureRandom();

    private final CodeRepository codeRepository;

    private String generateCode() {
        StringBuilder sb = new StringBuilder(6);

        for (int i = 0; i < 6; i++) {
            sb.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }

        return sb.toString();
    }

    @Transactional
    public String generateAndSaveCode() {
        String generatedCode = generateCode();

        Code code = new Code();
        code.setExpirationDate(LocalDateTime.now().plusSeconds(EXPIRATION_SECONDS));
        code.setActivationCode(generatedCode);

        codeRepository.save(code);

        return generatedCode;
    }

}
