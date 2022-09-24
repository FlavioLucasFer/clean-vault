package br.edu.ifpr.vault.domain.entities;

import java.security.SecureRandom;
import java.util.Base64;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level=AccessLevel.PRIVATE)
public class Token {
    @Getter
    String value;

    public Token() {
        this.value = this.generateToken();
    }

    private String generateToken() {
        final var secureRandom = new SecureRandom();
        final var base64encoder = Base64.getUrlEncoder();
        final var randomBytes = new byte[32];
        secureRandom.nextBytes(randomBytes);
        return base64encoder.encodeToString(randomBytes);
    }

    public boolean validate(final Token token) {
        return Token.validate(this, token);
    }

    @Override
    public boolean equals(final Object object) {
        return object == this || this.validate((Token) object);
    }

    public static boolean validate(final Token token, final Token token2) {
        return token.value == token2.value;
    }
}
