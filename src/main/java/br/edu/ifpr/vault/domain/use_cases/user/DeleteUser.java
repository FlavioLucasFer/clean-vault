package br.edu.ifpr.vault.domain.use_cases.user;

import br.edu.ifpr.vault.domain.entities.dtos.UserDTO;
import br.edu.ifpr.vault.domain.ports.services.UserServicePort;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class DeleteUser {
  @Getter
  UserDTO userDTO;

  public DeleteUser(final UserServicePort userService, final Long id) throws Exception {
    userService.deleteUser(id);
  }
}
