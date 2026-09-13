package com.octal.votehub.api.v1.mapper;

import com.octal.votehub.api.v1.domain.entity.Scheme;
import com.octal.votehub.api.v1.dto.scheme.ResponseSchemeDTO;
import org.modelmapper.ModelMapper;

public class SchemeMapper {

    private SchemeMapper() {
    }

    public static ResponseSchemeDTO toResponseSchemeDTO(Scheme scheme) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(scheme, ResponseSchemeDTO.class);
    }
}
