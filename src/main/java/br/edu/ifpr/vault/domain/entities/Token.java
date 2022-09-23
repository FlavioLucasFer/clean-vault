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

    public Token() {}

    public void generateToken() {
        final var secureRandom = new SecureRandom();
        final var base64encoder = Base64.getUrlEncoder();
        final var randomBytes = new byte[32];
        secureRandom.nextBytes(randomBytes);
        this.value = base64encoder.encodeToString(randomBytes);
    }

    public boolean validate(Token token) {
        return Token.validate(this, token);
    }

    @Override
    public boolean equals(Object object) {
        return object == this || this.validate((Token) object);
    }

    public static boolean validate(Token token, Token token2) {
        return token.value == token2.value;
    }
}
