package com.coletaconsciente.faleconosco.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class FaleConoscoRequestDto {
    
    private String name;
    private String phone;
    private String email;
    private String message;
    
}
