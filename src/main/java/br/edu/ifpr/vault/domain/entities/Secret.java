package br.edu.ifpr.vault.domain.entities;

import br.edu.ifpr.vault.domain.entities.dtos.SecretDTO;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class Secret {
    @Getter
    Long id = null;
    @Getter
    User user;
    @Getter
    Token token;
    @Getter
    @Setter
    String value;

    public Secret(final User user, final String value) {
        this.user = user;
        this.token = new Token();
        this.value = value;
    }

    public Secret(final SecretDTO secretDTO) {
        this.id = secretDTO.getId();
        this.user = new User(secretDTO.getUserDTO());
        this.token = secretDTO.getToken();
        this.value = secretDTO.getValue();
    }
    
    public SecretDTO toDTO() {
        return new SecretDTO(this.id, this.user.toDTO(), this.token, this.value);
    }
}
