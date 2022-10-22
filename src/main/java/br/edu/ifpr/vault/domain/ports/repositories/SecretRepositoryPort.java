package br.edu.ifpr.vault.domain.ports.repositories;

import br.edu.ifpr.vault.domain.entities.Secret;

public interface SecretRepositoryPort {
    Secret findByToken(final String value);
    Secret save(final Secret secret);
    void delete(final Secret secret);
}
