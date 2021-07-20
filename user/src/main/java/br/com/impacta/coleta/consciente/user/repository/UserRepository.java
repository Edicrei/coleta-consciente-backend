package br.com.impacta.coleta.consciente.user.repository;

import br.com.impacta.coleta.consciente.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
}
