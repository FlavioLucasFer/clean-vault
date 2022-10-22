package br.edu.ifpr.vault.infra.adapters.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

import br.edu.ifpr.vault.domain.entities.User;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")

public class UserEntity {
  @Getter
  @Setter
  private String email;
  @Getter
  @Setter
  private String password;

  public UserEntity() {
  }

  public UserEntity(User user) throws Exception {
    this.email = user.getEmail();
    this.password = user.getPassword();
  }

  public User toUser() throws Exception {
    return new User(this.email, this.password);
  }
}
