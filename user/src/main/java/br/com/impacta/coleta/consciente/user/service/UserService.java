package br.com.impacta.coleta.consciente.user.service;
import br.com.impacta.coleta.consciente.user.dto.UserDtoRequest;
import br.com.impacta.coleta.consciente.user.dto.UserDtoResponse;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserDtoResponse> findAll();
    UserDtoResponse findById(Long id);
    UserDtoResponse createUser(UserDtoRequest userDtoRequest);
    void delete(Long id);

}
