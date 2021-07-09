package br.com.impacta.coleta.consciente.user.dto;

import lombok.Data;

@Data
public class UserDtoResponse {

    private Long Id;
    private String name;
    private String email;

    public UserDtoResponse(Long id, String name, String email) {
        Id = id;
        this.name = name;
        this.email = email;
    }
}
