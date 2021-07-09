package br.com.impacta.coleta.consiente.authorizationserver.repository;

import br.com.impacta.coleta.consiente.authorizationserver.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, String>{
	
	Authority findByName(String name);
	
}