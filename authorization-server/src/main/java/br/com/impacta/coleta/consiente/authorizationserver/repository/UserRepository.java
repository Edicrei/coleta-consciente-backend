package br.com.impacta.coleta.consiente.authorizationserver.repository;

import br.com.impacta.coleta.consiente.authorizationserver.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
	
	@Query("SELECT u FROM user_account u WHERE LOWER(u.email) = LOWER(:email)")
    Optional<User> findByEmail(@Param("email") String email);
	
}
