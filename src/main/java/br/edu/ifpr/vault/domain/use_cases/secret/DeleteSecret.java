package br.edu.ifpr.vault.domain.use_cases.secret;

import br.edu.ifpr.vault.domain.entities.Secret;
import br.edu.ifpr.vault.domain.entities.User;
import br.edu.ifpr.vault.domain.entities.dtos.SecretDTO;
import br.edu.ifpr.vault.domain.ports.services.SecretServicePort;
import lombok.Getter;

public class DeleteSecret {
  Secret secret;
  @Getter
  SecretDTO secretDTO;

  public DeleteSecret(
    final SecretServicePort secretService, 
    final SecretDTO secretDTO) throws Exception {
    final User user = new User(secretDTO.getUserDTO());
    this.secret = new Secret(user, secretDTO.getValue());
    var tokenValue = this.secret.toDTO().getToken().getValue();
    secretService.deleteSecret(tokenValue);
  }
}
