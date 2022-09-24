package br.edu.ifpr.vault.domain.entities;

import lombok.experimental.FieldDefaults;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import br.edu.ifpr.vault.domain.entities.dtos.UserDTO;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@FieldDefaults(level=AccessLevel.PRIVATE)
public class User {

    @Getter
    @Setter
    long id;
    
    @Getter
    String email;

    @Getter
    String password;

    public User() {}

    public User(String email, String password) throws Exception {
        this.setEmail(email);
        this.setPassword(password);
    }

    public User(UserDTO userDTO) throws Exception {
        this.id = userDTO.getId();
        this.setEmail(userDTO.getEmail());
        this.setPassword(userDTO.getPassword());
    }

    public void setEmail(String email) throws Exception {
        if (!this.validateEmail(email)) {
            throw new Exception("Invalid Email");
        }
        
        this.email = email;
    }

    public void setPassword(String password) throws Exception {
        if (password.length() < 8 || password.length() > 20) {
            throw new Exception("Password length must be between 8 and 20 characters");
        }

        this.password = password;
    }

    private boolean validateEmail(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }

    public UserDTO toDTO() {
        return new UserDTO(this.id, this.email, this.password);
    }

}
