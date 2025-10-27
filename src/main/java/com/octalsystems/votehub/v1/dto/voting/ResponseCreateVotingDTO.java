package com.octalsystems.votehub.v1.dto.voting;

import com.octalsystems.votehub.v1.enums.SchemeType;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseCreateVotingDTO {
    private Long id;
    private String title;
    private String description;
    private String expirationDate;
    private String url;
    private boolean generateQrcode;
    private boolean justify;
    private SchemeType schemeType;
    private LocalDateTime createdDate;
}
