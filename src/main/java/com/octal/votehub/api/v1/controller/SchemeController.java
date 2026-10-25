package com.octal.votehub.api.v1.controller;

import com.octal.votehub.api.v1.domain.entity.Scheme;
import com.octal.votehub.api.v1.dto.scheme.ResponseSchemeDTO;
import com.octal.votehub.api.v1.mapper.SchemeMapper;
import com.octal.votehub.api.v1.service.SchemeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/schemes")
public class SchemeController {

    private final SchemeService schemeService;

    @GetMapping
    @PreAuthorize("hasRole('CLIENT')")
    public ResponseEntity<List<ResponseSchemeDTO>> getAll() { //fixme: adicionar paginação e ordenação
        List<Scheme> schemes = schemeService.findAll();

        List<ResponseSchemeDTO> response = schemes.stream() //mover para o package de mapper
                .map(SchemeMapper::toResponseSchemeDTO)
                .toList();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseSchemeDTO> getSchemeById(@PathVariable Long id){
        Scheme scheme = schemeService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(SchemeMapper.toResponseSchemeDTO(scheme));
    }
}
