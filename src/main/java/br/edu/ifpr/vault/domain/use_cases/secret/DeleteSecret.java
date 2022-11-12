package br.edu.ifpr.vault.domain.use_cases.secret;

import br.edu.ifpr.vault.domain.entities.Secret;
import br.edu.ifpr.vault.domain.entities.dtos.SecretDTO;
import br.edu.ifpr.vault.domain.ports.services.SecretServicePort;
import lombok.Getter;

public class DeleteSecret {
  Secret secret;
  @Getter
  SecretDTO secretDTO;

  public DeleteSecret(
    final SecretServicePort secretService, 
    final String token) throws Exception {
    secretService.deleteSecret(token);
  }
}
