package com.octal.votehub.api.v1.util;

import com.octal.votehub.api.v1.entity.Code;
import com.octal.votehub.api.v1.repository.CodeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.time.LocalDateTime;

@Slf4j
@Component
@RequiredArgsConstructor
public class CodeService {
    private static final String CHARACTERS = "ACDEHJLMRSTV0123456789";
    private static final long EXPIRATION_SECONDS = 600L; //10 minutos
    private static final SecureRandom random = new SecureRandom();

    private final CodeRepository codeRepository;

    private static String generate() {
        StringBuilder sb = new StringBuilder(6);

        for (int i = 0; i < 6; i++) {
            sb.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }

        return sb.toString();
    }

    private void isExpired(Code code){
        LocalDateTime dateTimeNow = LocalDateTime.now();
        LocalDateTime expiration = code.getExpirationDate();

        if (dateTimeNow.isAfter(expiration)) {
            log.info("'Código para ativação de conta expirado.'");
            throw new RuntimeException("'Código expirado.'");
        }
    }

    @Transactional
    public String generateAndSave() {
        String generatedCode = generate();

        Code code = new Code();
        code.setExpirationDate(LocalDateTime.now().plusSeconds(EXPIRATION_SECONDS));
        code.setActivationCode(generatedCode);

        codeRepository.save(code);

        return generatedCode;
    }

    @Transactional(readOnly = true)
    public void validate(String code){
        Code codeIsPresent = codeRepository.findByCode(code)
                .orElseThrow(() -> {
                    log.info("'O código de ativação de conta não confere.'");
                    return new RuntimeException("'O código recebido não está correto.'");
                });

        isExpired(codeIsPresent);
    }

}
