package br.edu.ifpr.vault.domain.entities.dtos;

import br.edu.ifpr.vault.domain.entities.Token;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class SecretDTO {
    @Getter
    Long id = null;
    @Getter
    UserDTO userDTO;
    @Getter
    Token token;
    @Getter
    String value;

    public SecretDTO(
        final Long id, 
        final UserDTO userDTO, 
        final Token token, 
        final String value) {
        this.id = id;
        this.userDTO = userDTO;
        this.token = token;
        this.value = value;
    }
}
