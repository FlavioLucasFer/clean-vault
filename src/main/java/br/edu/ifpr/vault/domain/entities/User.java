package br.edu.ifpr.vault.domain.entities;

import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import lombok.AccessLevel;
import lombok.Getter;

@FieldDefaults(level=AccessLevel.PRIVATE)
@Slf4j
public class User {
    @Getter
    Long id = null;
    @Getter
    String email;
    @Getter
    String password;

    public User() {}

    public User(final String email, final String password) throws Exception {
        this.setEmail(email);
        this.setPassword(password);
    }

    public void setEmail(final String email) throws Exception {
        if (!this.validateEmail(email)) {
            throw new Exception("Invalid Email");
        }
        
        this.email = email;
    }

    public void setPassword(final String password) throws Exception {
        if (password.length() < 8 || password.length() > 20) {
            throw new Exception("Password length must be between 8 and 20 characters");
        }

        this.password = password;
    }

    private boolean validateEmail(final String email) {
        boolean result = true;
        try {
            final InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (final AddressException exception) {
            User.log.info(exception.getMessage());
            result = false;
        }
        return result;
    }

}
