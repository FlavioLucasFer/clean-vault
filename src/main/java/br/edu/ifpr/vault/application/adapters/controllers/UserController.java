package br.edu.ifpr.vault.application.adapters.controllers;

import java.util.Objects;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.server.ResponseStatusException;

import br.edu.ifpr.vault.domain.entities.dtos.UserDTO;
import br.edu.ifpr.vault.domain.ports.services.UserServicePort;
import br.edu.ifpr.vault.domain.use_cases.user.CreateUser;
import br.edu.ifpr.vault.domain.use_cases.user.DeleteUser;
import br.edu.ifpr.vault.domain.use_cases.user.GetUser;
import br.edu.ifpr.vault.domain.use_cases.user.UpdateUser;

@RestController
@RequestMapping("user")
public class UserController {
  private final UserServicePort userServicePort;

  public UserController(UserServicePort userServicePort) {
    this.userServicePort = userServicePort;
  }

  @GetMapping
  UserDTO getUser(@RequestHeader("id") Long id) throws Exception {
    if (Objects.isNull(id))
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    var user = new GetUser(userServicePort, id);

    return user.getUserDTO();
  }

  @PostMapping
  void createUser(@RequestBody UserDTO data) {
    if (Objects.isNull(data))
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

    try {
      new CreateUser(userServicePort, data);
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
  }

  @PutMapping
  UserDTO updateSecret(@RequestBody UserDTO data) {
    if (Objects.isNull(data))
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

    try {
      var secret = new UpdateUser(userServicePort, data);
      return secret.getUserDTO();
    } catch (NotFoundException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
  }

  @DeleteMapping
  void deleteSecret(@RequestHeader("id") Long id) {
    if (Objects.isNull(id))
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

    try {
      new DeleteUser(userServicePort, id);
    } catch (NotFoundException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
  }
}
