package br.edu.ifpr.vault.domain.use_cases.user;

import br.edu.ifpr.vault.domain.entities.User;
import br.edu.ifpr.vault.domain.entities.dtos.UserDTO;
import br.edu.ifpr.vault.domain.ports.services.UserServicePort;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateUser {
  User user;
  @Getter
  UserDTO userDTO;

  public UpdateUser(final UserServicePort userService, final UserDTO userDTO) throws Exception {
    this.user = new User(userDTO);

    this.userDTO = userService.updateUser(this.user.toDTO().getEmail(), this.user.toDTO().getPassword());
  }
}
