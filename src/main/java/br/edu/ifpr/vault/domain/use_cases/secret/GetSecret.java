package br.edu.ifpr.vault.domain.use_cases.secret;

import br.edu.ifpr.vault.domain.entities.dtos.SecretDTO;
import br.edu.ifpr.vault.domain.ports.services.SecretServicePort;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class GetSecret {
  @Getter
  SecretDTO secretDTO;

  public GetSecret(
    final SecretServicePort secretService, 
    final String token) throws Exception {
    this.secretDTO = secretService.getSecret(token);
  }
}
