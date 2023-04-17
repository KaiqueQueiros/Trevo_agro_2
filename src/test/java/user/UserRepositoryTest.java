package user;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import trevo.agro2.br.api.ApiApplication;
import trevo.agro2.br.api.dto.user.RoleEnum;
import trevo.agro2.br.api.model.User;
import trevo.agro2.br.api.repository.UserRepository;
import trevo.agro2.br.api.security.TokenService;
import trevo.agro2.br.api.service.UserService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
@SpringBootTest(classes = ApiApplication.class)
@AutoConfigureMockMvc
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @MockBean
    private UserService userService;
    @Autowired
    TokenService tokenService;


    @Test
    public void testUserDetails() {
        User user = new User();
        user.setName("John Doe");
        user.setLogin("johndoe@example.com");
        user.setPassword("password123");
        user.setRole(RoleEnum.ADMINISTRADOR);
        user.setDate("12/12/2012");

        User savedUser = userRepository.save(user);

        UserDetails userDetails = userRepository.findByLogin("johndoe@example.com");

        assertNotNull(userDetails);
        assertEquals(user.getLogin(), userDetails.getUsername());
        assertEquals(user.getPassword(), userDetails.getPassword());
    }

    @Test
    @DisplayName("Não deve permitir que o usuario cliente acesse o endpoint /user/register")
    public void testClientAccess() throws Exception {
        User user = new User();
        user.setName("Kaique");
        user.setLogin("kaique.q@outlook.com");
        user.setPassword("123456789");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(RoleEnum.CLIENTE);
        userRepository.save(user);
        String token = tokenService.token(user);
        mockMvc.perform(post("/user/register")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("Deve permitir que o usuario administrador acesse o endpoint /user/register")
    public void testAdminAccess() throws Exception {
        User user = new User();
        user.setName("Kaique");
        user.setLogin("usuario@teste.com");
        user.setPassword("123456");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(RoleEnum.ADMINISTRADOR);
        userRepository.save(user);
        String token = tokenService.token(user);
        System.out.println(token);
        mockMvc.perform(post("/user/register")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Não deve permitir que o usuario colaborador acesse o endpoint /user/register")
    public void testColaboratorAccess() throws Exception {
        User user = new User();
        user.setName("Kaique");
        user.setLogin("kaique.q@hotmail.com");
        user.setPassword("123456789");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(RoleEnum.COLABORADOR);
        userRepository.save(user);
        String token = tokenService.token(user);
        mockMvc.perform(post("/user/register")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isForbidden());
    }

    @Test
    public void testTokenAcess() throws Exception {
        User user = new User();
        user.setName("Kaique");
        user.setLogin("kaique.q@gmail.com");
        user.setPassword("123456789");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(RoleEnum.COLABORADOR);
        userRepository.save(user);
        String token = tokenService.token(user);
        mockMvc.perform(post("/user/login")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk());
    }


}
