package br.com.impacta.coleta.consciente.user.service;
import br.com.impacta.coleta.consciente.user.dto.UserDtoRequest;
import br.com.impacta.coleta.consciente.user.dto.UserDtoResponse;
import br.com.impacta.coleta.consciente.user.dto.UserDtoUpdate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface UserService {
    List<UserDtoResponse> findAll();
    UserDtoResponse findById(Long id);
    UserDtoResponse createUser(UserDtoRequest userDtoRequest);
    void delete(Long id);
    UserDtoResponse update(UserDtoUpdate userDtoRequest, Long id);
    UserDtoResponse findByEmail(String email);


}
