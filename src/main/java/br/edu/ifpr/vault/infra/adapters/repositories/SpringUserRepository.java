package br.edu.ifpr.vault.infra.adapters.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifpr.vault.infra.adapters.entities.UserEntity;

@Repository
public interface SpringUserRepository extends JpaRepository<UserEntity, Long> {
  Optional<UserEntity> findById(Long id);
}
