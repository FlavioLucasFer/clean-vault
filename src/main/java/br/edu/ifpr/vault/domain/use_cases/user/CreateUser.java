package br.edu.ifpr.vault.domain.use_cases.user;

import br.edu.ifpr.vault.domain.entities.User;
import br.edu.ifpr.vault.domain.entities.dtos.UserDTO;
import br.edu.ifpr.vault.domain.ports.UserRepository;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateUser {
    User user;
    @Getter
    UserDTO userDTO;

    public CreateUser(final UserRepository userRepository, final UserDTO userDTO) throws Exception {
        this.user = new User(userDTO);

        this.userDTO = userRepository.save(this.user.toDTO());
    }
}
