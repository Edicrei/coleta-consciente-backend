package br.com.impacta.coleta.consciente.user.dto;

import lombok.Data;

@Data
public class UserDtoRequest {
    private String name;
    private String password;
    private String email;

    public UserDtoRequest(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
    }
}
