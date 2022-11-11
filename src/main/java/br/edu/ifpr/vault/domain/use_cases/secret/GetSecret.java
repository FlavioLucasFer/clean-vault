package br.edu.ifpr.vault.domain.use_cases.secret;

import br.edu.ifpr.vault.domain.entities.Secret;
import br.edu.ifpr.vault.domain.entities.User;
import br.edu.ifpr.vault.domain.entities.dtos.SecretDTO;
import br.edu.ifpr.vault.domain.ports.services.SecretServicePort;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class GetSecret {
  Secret secret;
  @Getter
  SecretDTO secretDTO;

  public GetSecret(
    final SecretServicePort secretService, 
    final SecretDTO secretDTO) throws Exception {
    final User user = new User(secretDTO.getUserDTO());
    this.secret = new Secret(user, secretDTO.getValue());
    var tokenValue = this.secret.toDTO().getToken().getValue();
    this.secretDTO = secretService.getSecret(tokenValue);
  }
}
