package com.coletaconsciente.faleconosco.repository;

import java.util.List;

import com.coletaconsciente.faleconosco.domain.FaleConosco;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FaleConoscoRepository extends JpaRepository<FaleConosco, Long> {

    List<FaleConosco> findByEmailIgnoreCaseContaining(String email);
}
