package br.edu.ifpr.vault.domain.entities;

import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.edu.ifpr.vault.domain.entities.dtos.UserDTO;
import lombok.AccessLevel;
import lombok.Getter;

@FieldDefaults(level=AccessLevel.PRIVATE)
@Slf4j
public class User {
    @Getter
    Long id;
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
        String regex = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
            + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(email);

            return matcher.matches();
    }

    public UserDTO toDTO() {
        return new UserDTO(this.id, this.email, this.password);
    }

}
