package br.edu.ifpr.vault.infra.adapters.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import br.edu.ifpr.vault.domain.entities.Secret;
import br.edu.ifpr.vault.domain.entities.Token;
import br.edu.ifpr.vault.domain.entities.User;

@Entity
@Table(name = "secrets")
public class SecretEntity {
  @Getter
  @Setter
  private User user;
  @Getter
  @Setter
  private Token token;
  @Getter
  @Setter
  private String value;

  public SecretEntity() {
  }

  public SecretEntity(User user, Secret secret) throws Exception {
    this.user = secret.getUser();
    this.token = secret.getToken();
    this.value = secret.getValue();
  }

  public Secret toSecret() {
    return new Secret(this.user, this.value);
  }
}
