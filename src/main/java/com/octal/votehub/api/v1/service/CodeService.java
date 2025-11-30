package com.octal.votehub.api.v1.service;

import com.octal.votehub.api.v1.entity.Code;
import com.octal.votehub.api.v1.repository.CodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class CodeService {

    private static final String CHARACTERS = "ACDEHJLMRSTV0123456789";
    private static final long EXPIRATION_MILLIS = 300000L; //5 minutos
    private static final Random random = new Random();

    private final CodeRepository codeRepository;

    public static String generateCode() {
        StringBuilder sb = new StringBuilder(6);

        for (int i = 0; i < 6; i++) {
            sb.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }

        return sb.toString();
    }

    @Transactional
    public void saveActivationCode(String activationCode) {
        Code code = new Code();
        code.setActivationCode(activationCode);

        codeRepository.save(code);
    }

}
