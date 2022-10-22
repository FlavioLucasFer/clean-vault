package br.edu.ifpr.vault.application.adapters.services;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import br.edu.ifpr.vault.domain.entities.User;
import br.edu.ifpr.vault.domain.entities.dtos.UserDTO;
import br.edu.ifpr.vault.domain.ports.repositories.UserRepositoryPort;
import br.edu.ifpr.vault.domain.ports.services.UserServicePort;

public class UserService implements UserServicePort {

  private final UserRepositoryPort userRepository;

  public UserService(UserRepositoryPort userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDTO getUser(Long id) {
    return null;
  }

  @Override
  public UserDTO createUser(String email, String password) throws Exception {
    return this.userRepository.save(new User(email, password)).toDTO();
  }

  @Override
  public UserDTO updateUser(String email, String password) throws NotFoundException, Exception {
    return null;
  }

  @Override
  public void deleteUser(Long id) throws NotFoundException, Exception {
    
  }
  
}
