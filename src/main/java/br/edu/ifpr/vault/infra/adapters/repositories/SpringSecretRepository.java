package br.edu.ifpr.vault.infra.adapters.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifpr.vault.infra.adapters.entities.SecretEntity;

@Repository
public interface SpringSecretRepository extends JpaRepository<SecretEntity, Long> {
  Optional<SecretEntity> findByToken(String token);
}
