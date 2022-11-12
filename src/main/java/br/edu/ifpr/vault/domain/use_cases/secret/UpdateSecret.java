package br.edu.ifpr.vault.domain.use_cases.secret;

import br.edu.ifpr.vault.domain.entities.dtos.SecretDTO;
import br.edu.ifpr.vault.domain.ports.services.SecretServicePort;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateSecret {
  @Getter
  SecretDTO secretDTO;

  public UpdateSecret(
    final SecretServicePort secretService, 
    final String token,
    final String value) throws Exception {
    this.secretDTO = secretService.updateSecret(token, value);
  }
}
