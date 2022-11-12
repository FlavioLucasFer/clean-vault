package br.edu.ifpr.vault.application.adapters.controllers;

import java.util.Objects;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.edu.ifpr.vault.domain.entities.dtos.SecretDTO;
import br.edu.ifpr.vault.domain.ports.services.SecretServicePort;
import br.edu.ifpr.vault.domain.use_cases.secret.CreateSecret;
import br.edu.ifpr.vault.domain.use_cases.secret.DeleteSecret;
import br.edu.ifpr.vault.domain.use_cases.secret.GetSecret;
import br.edu.ifpr.vault.domain.use_cases.secret.UpdateSecret;

@RestController
@RequestMapping("secret")
public class SecretController {
  private final SecretServicePort secretServicePort;

  public SecretController(SecretServicePort secretServicePort) {
    this.secretServicePort = secretServicePort;
  }

  @GetMapping
  SecretDTO getSecret(@RequestHeader("token") String token) throws Exception {
    if (Objects.isNull(token) || token.isEmpty())
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    var secret = new GetSecret(secretServicePort, token);

    return secret.getSecretDTO();
  }

  @PostMapping
  void createSecret(@RequestBody SecretDTO data) {
    if (Objects.isNull(data))
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

    try {
      new CreateSecret(secretServicePort, data);
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
  }

  @PutMapping
  SecretDTO updateSecret(@RequestHeader("token") String token, @RequestBody String data) {
    if (Objects.isNull(token) || token.isEmpty())
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

    try {
      var secret = new UpdateSecret(secretServicePort, token, data);
      return secret.getSecretDTO();
    } catch (NotFoundException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
  }

  @DeleteMapping
  void deleteSecret(@RequestHeader("token") String token) {
    if (Objects.isNull(token) || token.isEmpty())
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

    try {
      new DeleteSecret(secretServicePort, token);
    } catch (NotFoundException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    } catch (Exception e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
  }
}
