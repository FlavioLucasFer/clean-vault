package br.edu.ifpr.vault.domain.ports.repositories;

import br.edu.ifpr.vault.domain.entities.User;

public interface UserRepositoryPort {
    User findById(final Long id);
    User save(final User user);
    void delete(final User user);
}
