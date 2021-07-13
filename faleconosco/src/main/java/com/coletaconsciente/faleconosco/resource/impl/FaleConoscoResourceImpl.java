package com.coletaconsciente.faleconosco.resource.impl;

import java.util.List;

import com.coletaconsciente.faleconosco.dto.FaleConoscoRequestDto;
import com.coletaconsciente.faleconosco.dto.FaleConoscoResponseDto;
import com.coletaconsciente.faleconosco.resource.FaleConoscoResource;
import com.coletaconsciente.faleconosco.service.FaleConoscoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(value = "/fale-conosco")
public class FaleConoscoResourceImpl implements FaleConoscoResource {

    @Autowired
    private FaleConoscoService faleConoscoService;

    @GetMapping
    public List<FaleConoscoResponseDto> findByEmail(@RequestParam(required = false) String email) {
        return faleConoscoService.getForms(email);
    }

    @GetMapping("/{id}")
    public FaleConoscoResponseDto findById(@PathVariable Long id) {
        return faleConoscoService.getFormById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FaleConoscoResponseDto create(@RequestBody FaleConoscoRequestDto faleConoscoRequestDto) {
        try {
            return faleConoscoService.postForm(faleConoscoRequestDto);
        } catch (Exception error) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Algo Horrível aconteceu!", error
            );
        }
    }

    @PutMapping("/{id}")
    public FaleConoscoResponseDto update(@RequestBody FaleConoscoRequestDto faleConoscoRequestDto, @PathVariable Long id) {
        try {
            return faleConoscoService.putForm(faleConoscoRequestDto, id);
        } catch (Exception error) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Algo Horrível aconteceu!", error
            );
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        faleConoscoService.deleteForm(id);
    }

}
