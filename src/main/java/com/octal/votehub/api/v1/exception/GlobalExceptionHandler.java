package com.octal.votehub.api.v1.exception;

import com.octal.votehub.api.v1.dto.ExceptionMessageDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(InvalidEmailException.class)
    public ResponseEntity<ExceptionMessageDTO> InvalidEmailException(InvalidEmailException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ExceptionMessageDTO(HttpStatus.NOT_FOUND.value(), exception.getMessage()));
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(AccountAlreadyActivatedException.class)
    public ResponseEntity<ExceptionMessageDTO> AccountAlreadyActivatedException(AccountAlreadyActivatedException exception){
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new ExceptionMessageDTO(HttpStatus.CONFLICT.value(), exception.getMessage()));
    }

}
