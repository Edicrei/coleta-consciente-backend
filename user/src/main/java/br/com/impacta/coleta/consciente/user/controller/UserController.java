package br.com.impacta.coleta.consciente.user.controller;

import br.com.impacta.coleta.consciente.user.dto.UserDtoRequest;
import br.com.impacta.coleta.consciente.user.dto.UserDtoResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface UserController {

    UserDtoResponse create(UserDtoRequest userDtoRequest);
    UserDtoResponse findById(Long id);
    List<UserDtoResponse> findAll();
    void delete(Long id);

}
