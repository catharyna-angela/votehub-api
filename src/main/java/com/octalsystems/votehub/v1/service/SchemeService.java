package com.octalsystems.votehub.v1.service;

import com.octalsystems.votehub.v1.entity.Scheme;
import com.octalsystems.votehub.v1.repository.SchemeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
