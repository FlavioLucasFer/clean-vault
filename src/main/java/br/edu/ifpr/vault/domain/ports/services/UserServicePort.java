package br.edu.ifpr.vault.domain.ports.services;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import br.edu.ifpr.vault.domain.entities.dtos.UserDTO;

public interface UserServicePort {
    UserDTO getUser(final Long id);
    UserDTO createUser(final String email, final String password) throws Exception;
    UserDTO updateUser(final Long id, final String email, final String password) throws NotFoundException, Exception;
    void deleteUser(Long id) throws NotFoundException, Exception;
}
