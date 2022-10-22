package br.edu.ifpr.vault.domain.use_cases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.edu.ifpr.vault.application.adapters.services.UserService;
import br.edu.ifpr.vault.domain.entities.dtos.UserDTO;
import br.edu.ifpr.vault.domain.use_cases.user.CreateUser;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserUT {
    @Mock
    UserService mockedUserService = mock(UserService.class);

    @BeforeAll
    public void beforeAll() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void givenValidInput_whenInstantiate_shouldCreateUser() {
        var userDTO = new UserDTO(1L, "test@mail.com", "testpass");
        var createUser = this.createUser(userDTO);
        assertNotEquals(createUser, null);
    }

    @Test
    public void givenInvalidInput_whenInstantiate_shouldNotCreateUser() {
        var wrongEmailUserDTO = new UserDTO(1L, "@mail.com", "testpass");
        var wrongPasswordUserDTO = new UserDTO(1L, "test@mail.com", "test");
        
        var wrongEmailUser = this.createUser(wrongEmailUserDTO);
        var wrongPasswordUser = this.createUser(wrongPasswordUserDTO);
        
        assertEquals(wrongEmailUser, null);
        assertEquals(wrongPasswordUser, null);
    }

    private CreateUser createUser(final UserDTO userDTO) {
        try {
            doReturn(userDTO)
                .when(this.mockedUserService)
                .createUser(null, null);

            return new CreateUser(this.mockedUserService, userDTO);
        } catch (Exception e) {
            return null;
        }
    }
}
