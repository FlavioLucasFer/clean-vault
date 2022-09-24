package br.edu.ifpr.vault.domain.ports;

import br.edu.ifpr.vault.domain.entities.dtos.SecretDTO;

public interface SecretRepository {
    SecretDTO findByToken(final String value);
    SecretDTO save(final SecretDTO secretDTO);
    void delete(final SecretDTO secretDTO);
}
