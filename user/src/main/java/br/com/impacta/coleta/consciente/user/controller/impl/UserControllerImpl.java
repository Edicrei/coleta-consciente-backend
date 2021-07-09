package br.com.impacta.coleta.consciente.user.controller.impl;

import br.com.impacta.coleta.consciente.user.controller.UserController;
import br.com.impacta.coleta.consciente.user.dto.UserDtoRequest;
import br.com.impacta.coleta.consciente.user.dto.UserDtoResponse;
import br.com.impacta.coleta.consciente.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/user")
public class UserControllerImpl implements UserController {

    @Autowired
    private UserService userService;

    @Override
    @PostMapping
    public UserDtoResponse create(@RequestBody UserDtoRequest userDtoRequest) {
        return this.userService.createUser(userDtoRequest);
    }

    @Override
    @GetMapping("/{id}")
    public UserDtoResponse findById(@PathVariable Long id) {
        return this.userService.findById(id);
    }

    @Override
    @GetMapping
    public List<UserDtoResponse> findAll() {
        return this.userService.findAll();
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        this.userService.delete(id);
    }
}
