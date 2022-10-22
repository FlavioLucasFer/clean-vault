package br.edu.ifpr.vault.domain.ports.services;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import br.edu.ifpr.vault.domain.entities.User;
import br.edu.ifpr.vault.domain.entities.dtos.SecretDTO;

public interface SecretServicePort {
    SecretDTO getSecret(String token);
    SecretDTO createSecret(User user, String value) throws Exception;
    SecretDTO updateSecret(String token, String value) throws NotFoundException, Exception;
    void deleteSecret(String token) throws NotFoundException, Exception;
}
