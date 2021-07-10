package br.com.impacta.coleta.consciente.user.controller;

import br.com.impacta.coleta.consciente.user.dto.UserDtoRequest;
import br.com.impacta.coleta.consciente.user.dto.UserDtoResponse;
import br.com.impacta.coleta.consciente.user.dto.UserDtoUpdate;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@ApiOperation(value = "User Controller", notes = "Apis responsáveis por controle de usuários")
@ApiResponses(value = {
        @ApiResponse(code = 200, message = "Operação executada com sucesso"),
        @ApiResponse(code = 400, message = "error, verify your request and try again")
})
public interface UserController {

    UserDtoResponse create(UserDtoRequest userDtoRequest);
    UserDtoResponse findById(Long id);
    List<UserDtoResponse> findAll();
    void delete(Long id);
    UserDtoResponse update(@RequestBody UserDtoUpdate userDtoRequest, @PathVariable Long id);

}
