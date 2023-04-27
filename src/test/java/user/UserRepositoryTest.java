package user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import trevo.agro2.br.api.ApiApplication;
import trevo.agro2.br.api.dto.user.RoleEnum;
import trevo.agro2.br.api.model.User;
import trevo.agro2.br.api.repository.UserRepository;
import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
@SpringBootTest(classes = ApiApplication.class)
@AutoConfigureMockMvc
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder encoder;
    @Test
    @DisplayName("Deve criar um usuario e persistir os dados")
    public void testUserDetails() {
        User user = new User();
        user.setName("John Doe");
        user.setLogin("johndoe@example.com");
        user.setPassword("password123");
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRole(RoleEnum.ADMINISTRADOR);
        userRepository.save(user);
        UserDetails userDetails = userRepository.findByLogin("johndoe@example.com");
        assertNotNull(userDetails);
        assertEquals(user.getLogin(), userDetails.getUsername());
        assertEquals(user.getPassword(), userDetails.getPassword());
    }
    @Test
    @DisplayName("Deve deletar um usuario")
    public void testDeleteUser() {
        User user = new User();
        user.setName("John");
        user.setLogin("johndo@hotmail.com");
        user.setPassword("password123");
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRole(RoleEnum.ADMINISTRADOR);
        userRepository.save(user);
        userRepository.deleteById(user.getId());
        assertFalse(userRepository.existsById(user.getId()));
    }
    @Test
    public void whenUpdateUser(){
        User user = new User();
        user.setName("John");
        user.setLogin("Orlando@hotmail.com");
        user.setPassword("password123");
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRole(RoleEnum.ADMINISTRADOR);
        userRepository.save(user);
        user.setName("Henrique");
        user.setLogin("Thiago.farias@hotmail.com");
        user.setPassword("123456789");
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRole(RoleEnum.COLABORADOR);
        userRepository.save(user);
        UserDetails userDetails = userRepository.findByLogin("Thiago.farias@hotmail.com");
        assertNotNull(userDetails);
        assertEquals(user.getLogin(), userDetails.getUsername());
        assertEquals(user.getPassword(), userDetails.getPassword());
    }
    
}
