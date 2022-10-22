package br.edu.ifpr.vault.application.adapters.services;

import java.util.Objects;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import br.edu.ifpr.vault.domain.entities.Secret;
import br.edu.ifpr.vault.domain.entities.User;
import br.edu.ifpr.vault.domain.entities.dtos.SecretDTO;
import br.edu.ifpr.vault.domain.ports.repositories.SecretRepositoryPort;
import br.edu.ifpr.vault.domain.ports.services.SecretServicePort;

public class SecretService implements SecretServicePort {
  private final SecretRepositoryPort secretRepository;

  public SecretService(SecretRepositoryPort secretRepository) {
    this.secretRepository = secretRepository;
  }

  @Override
  public SecretDTO getSecret(String token) {
    return this.secretRepository.findByToken(token).toDTO();
  }

  @Override
  public SecretDTO createSecret(User user, String value) throws Exception {
    return this.secretRepository.save(new Secret(user, value)).toDTO();
  }

  @Override
  public SecretDTO updateSecret(String token, String value) throws NotFoundException, Exception {
    var secret = this.find(token);
    secret.setValue(value);
    return this.secretRepository.save(secret).toDTO();
  }

  @Override
  public void deleteSecret(String token) throws NotFoundException, Exception {
    var secret = this.find(token);
    this.secretRepository.delete(secret);
  }

  private Secret find(String token) throws NotFoundException {
    var secret = secretRepository.findByToken(token);

    if (Objects.isNull(secret))
      throw new NotFoundException();

    return secret;
  }
}
