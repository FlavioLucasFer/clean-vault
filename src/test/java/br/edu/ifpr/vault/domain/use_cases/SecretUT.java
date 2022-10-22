package br.edu.ifpr.vault.domain.use_cases;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.edu.ifpr.vault.application.adapters.services.SecretService;
import br.edu.ifpr.vault.domain.entities.Token;
import br.edu.ifpr.vault.domain.entities.dtos.SecretDTO;
import br.edu.ifpr.vault.domain.entities.dtos.UserDTO;
import br.edu.ifpr.vault.domain.use_cases.secret.CreateSecret;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SecretUT {
    @Mock
    SecretService mockedSecretService = mock(SecretService.class);
    
    @BeforeAll
    public void beforeAll() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void givenValidInput_whenInstantiate_shouldCreateSecret() {
        var createSecret = this.createSecret();
        assertNotEquals(createSecret, null);
    }

    private CreateSecret createSecret() {
        var token = new Token();
        var userDTO = new UserDTO(1L, "user@gmail.com", "testpass");
        var secretDTO = new SecretDTO(1L, userDTO, token, "secret");
        
        try {
            doReturn(secretDTO)
                .when(this.mockedSecretService)
                .createSecret(null, null);

            return new CreateSecret(this.mockedSecretService, secretDTO);
        } catch (Exception e) {
            return null;
        }
    }
}
