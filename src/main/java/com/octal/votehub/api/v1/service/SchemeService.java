package com.octal.votehub.api.v1.service;

import com.octal.votehub.api.v1.domain.entity.Scheme;
import com.octal.votehub.api.v1.repository.SchemeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class SchemeService {

    private final SchemeRepository schemeRepository;

    @Transactional(readOnly = true)
    public List<Scheme> findAll(){
        return schemeRepository.findAll();
    }

    @Transactional
    public Scheme findById(Long id) {
        Scheme scheme = schemeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Esquema não consta."));

        scheme.dateExpired(LocalDateTime.now());

        return scheme;
    }
}
