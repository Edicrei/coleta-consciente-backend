package com.coletaconsciente.location.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coletaconsciente.location.domain.model.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

}