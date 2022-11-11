package br.edu.ifpr.vault.application.adapters.services;

import java.util.Objects;

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
    return this.userRepository.findById(id).toDTO();
  }

  @Override
  public UserDTO createUser(String email, String password) throws Exception {
    return this.userRepository.save(new User(email, password)).toDTO();
  }

  @Override
  public UserDTO updateUser(Long id, String email, String password) throws NotFoundException, Exception {
    var user = this.find(id);
    user.setEmail(email);
    user.setPassword(password);
    return this.userRepository.save(user).toDTO();
  }

  @Override
  public void deleteUser(Long id) throws NotFoundException, Exception {
    var user = this.find(id);
    this.userRepository.delete(user);
  }

  private User find(Long id) throws NotFoundException {
    var user = userRepository.findById(id);

    if (Objects.isNull(user))
      throw new NotFoundException();

    return user;
  }
  
}
