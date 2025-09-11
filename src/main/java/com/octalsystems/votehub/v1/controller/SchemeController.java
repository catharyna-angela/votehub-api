package com.octalsystems.votehub.v1.controller;

import com.octalsystems.votehub.v1.entity.Scheme;
import com.octalsystems.votehub.v1.service.SchemeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Scheme>> getAll(){ //fixme: implementar paginação e permissão por id, role_client e role_admin.
        List<Scheme> scheme = schemeService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(scheme);
    }
}
