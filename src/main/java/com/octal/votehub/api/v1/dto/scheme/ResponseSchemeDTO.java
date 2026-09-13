package com.octal.votehub.api.v1.dto.scheme;

import com.octal.votehub.api.v1.domain.enums.SchemeType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseSchemeDTO {
    private String title;
    private SchemeType schemeType;
    private String description;
    private LocalDateTime expirationDate;
    //private boolean isExpired;

}
