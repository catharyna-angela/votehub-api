package com.octal.votehub.api.v1.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ExceptionMessageDTO {
    public final int statusCode;
    public final String message;

    public ExceptionMessageDTO(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

}
