package br.edu.ifpr.vault.domain.ports;

import br.edu.ifpr.vault.domain.entities.dtos.UserDTO;

public interface UserRepository {
    UserDTO findById(final Long id);
    UserDTO save(final UserDTO userDTO);
    void delete(final UserDTO userDTO);
}
