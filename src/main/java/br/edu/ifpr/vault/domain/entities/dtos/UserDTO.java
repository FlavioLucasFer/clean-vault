package br.edu.ifpr.vault.domain.entities.dtos;

import lombok.Getter;

public class UserDTO {
    @Getter
    Long id;
    @Getter
    String email;
    @Getter
    String password;

    public UserDTO(final Long id, final String email, final String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }
}
