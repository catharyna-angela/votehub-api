package com.octal.votehub.api.v1.controller;

import com.octal.votehub.api.v1.domain.entity.Scheme;
import com.octal.votehub.api.v1.dto.scheme.ResponseSchemeDTO;
import com.octal.votehub.api.v1.mapper.SchemeMapper;
import com.octal.votehub.api.v1.service.SchemeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
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
    public ResponseEntity<List<ResponseSchemeDTO>> getAll() {
        List<Scheme> schemes = schemeService.findAll();

        List<ResponseSchemeDTO> response = schemes.stream()
                .map(SchemeMapper::toResponseSchemeDTO)
                .toList();

        return ResponseEntity.ok(response);
    }

    //getSchemeById
}
