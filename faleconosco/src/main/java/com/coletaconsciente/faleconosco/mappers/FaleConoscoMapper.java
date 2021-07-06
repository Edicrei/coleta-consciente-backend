package com.coletaconsciente.faleconosco.mappers;

import com.coletaconsciente.faleconosco.domain.FaleConosco;
import com.coletaconsciente.faleconosco.dto.FaleConoscoRequestDto;
import com.coletaconsciente.faleconosco.dto.FaleConoscoResponseDto;

public class FaleConoscoMapper {

    public static FaleConoscoResponseDto mapFaleConoscoToFaleConoscoDto (FaleConosco faleConosco) {
        return FaleConoscoResponseDto.builder()
            .email(faleConosco.getEmail())
            .name(faleConosco.getName())
            .phone(faleConosco.getPhone())
            .message(faleConosco.getMessage()).build();
    }

    public static FaleConosco mapeFaleConoscoRequestDtoToFaleConosco (FaleConoscoRequestDto faleConoscoRequestDto) {
        return FaleConosco.builder()
            .email(faleConoscoRequestDto.getEmail())
            .name(faleConoscoRequestDto.getName())
            .phone(faleConoscoRequestDto.getPhone())
            .message(faleConoscoRequestDto.getMessage())
            .build();
    }
    
}
