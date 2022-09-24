package br.edu.ifpr.vault.domain.entities;

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

    public Secret(final User user) {
        this.user = user;
        this.token = new Token();
    }
}
