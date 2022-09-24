package br.edu.ifpr.vault.domain.use_cases.secret;

import br.edu.ifpr.vault.domain.entities.Secret;
import br.edu.ifpr.vault.domain.entities.User;
import br.edu.ifpr.vault.domain.entities.dtos.SecretDTO;
import br.edu.ifpr.vault.domain.entities.dtos.UserDTO;
import br.edu.ifpr.vault.domain.ports.SecretRepository;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateSecret {
    Secret secret;
    @Getter
    SecretDTO secretDTO;

    public CreateSecret(
        final SecretRepository secretRepository, 
        final SecretDTO secretDTO) {
        final User user = new User(secretDTO.getUserDTO());
        this.secret = new Secret(user, secretDTO.getValue());
        this.secretDTO = secretRepository.save(this.secret.toDTO());
    }
}
