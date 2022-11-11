package br.edu.ifpr.vault.domain.use_cases.user;

import br.edu.ifpr.vault.domain.entities.User;
import br.edu.ifpr.vault.domain.entities.dtos.UserDTO;
import br.edu.ifpr.vault.domain.ports.services.UserServicePort;
import lombok.Getter;

public class DeleteUser {
  User user;
  @Getter
  UserDTO userDTO;

  public DeleteUser(final UserServicePort userService, final UserDTO userDTO) throws Exception {
    this.user = new User(userDTO);

    userService.deleteUser(this.user.toDTO().getId());
  }
}
