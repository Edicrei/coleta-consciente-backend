package com.coletaconsciente.faleconosco.resource;

import java.util.List;

import com.coletaconsciente.faleconosco.dto.FaleConoscoRequestDto;
import com.coletaconsciente.faleconosco.dto.FaleConoscoResponseDto;

public interface FaleConoscoResource {
    
    public List<FaleConoscoResponseDto> findByEmail(String email);

    public FaleConoscoResponseDto findById(Long id);

    public FaleConoscoResponseDto create(FaleConoscoRequestDto data);

    public FaleConoscoResponseDto update(FaleConoscoRequestDto data, Long id);

    public void delete(Long id);
}
