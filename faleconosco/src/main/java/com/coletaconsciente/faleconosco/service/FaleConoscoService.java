package com.coletaconsciente.faleconosco.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.apache.commons.validator.routines.EmailValidator;
import com.coletaconsciente.faleconosco.domain.FaleConosco;
import com.coletaconsciente.faleconosco.dto.FaleConoscoRequestDto;
import com.coletaconsciente.faleconosco.dto.FaleConoscoResponseDto;
import com.coletaconsciente.faleconosco.exceptions.InvalidInputException;
import com.coletaconsciente.faleconosco.repository.FaleConoscoRepository;
import com.coletaconsciente.faleconosco.mappers.FaleConoscoMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FaleConoscoService {

    @Autowired
    private FaleConoscoRepository faleConoscoRepository;
    
    public List<FaleConoscoResponseDto> getForms(String email) {
        List<FaleConosco> faleConosco;
        if (email != null) {
            faleConosco = faleConoscoRepository.findByEmailIgnoreCaseContaining(email);
        } else {
            faleConosco = faleConoscoRepository.findAll();
        }
        return faleConosco
                .parallelStream()
                .map(FaleConoscoMapper::mapFaleConoscoToFaleConoscoDto)
                .collect(Collectors.toList());
    }

    public FaleConoscoResponseDto getFormById(Long id) {
        return FaleConoscoMapper.
            mapFaleConoscoToFaleConoscoDto(
                faleConoscoRepository.findById(id).orElseThrow(EntityNotFoundException::new)
            );
    }

    public FaleConoscoResponseDto postForm(FaleConoscoRequestDto faleConoscoRequestDto) throws InvalidInputException {

        if (!EmailValidator.getInstance().isValid(faleConoscoRequestDto.getEmail())) {
            throw new InvalidInputException("Email Inválido!");
        }
        return FaleConoscoMapper.mapFaleConoscoToFaleConoscoDto(
            faleConoscoRepository.save(FaleConoscoMapper.mapeFaleConoscoRequestDtoToFaleConosco(faleConoscoRequestDto)));
    }

    public FaleConoscoResponseDto putForm(FaleConoscoRequestDto faleConoscoRequestDto, Long id) throws InvalidInputException {
        Optional<FaleConosco> faleConosco = faleConoscoRepository.findById(id);
        if (faleConosco.isEmpty()) {
            return postForm(faleConoscoRequestDto);
        } else {
            FaleConosco obj = faleConosco.get();
            obj.setName(faleConoscoRequestDto.getName());
            obj.setPhone(faleConoscoRequestDto.getPhone());
            obj.setEmail(faleConoscoRequestDto.getMessage());
            return FaleConoscoMapper.mapFaleConoscoToFaleConoscoDto(faleConoscoRepository.save(obj));
        }
    }

    public void deleteForm(Long id) {
        faleConoscoRepository.deleteById(id);
    }

}
