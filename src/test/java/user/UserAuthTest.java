//package user;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.web.servlet.MockMvc;
//import trevo.agro2.br.api.ApiApplication;
//import trevo.agro2.br.api.dto.product.Category;
//import trevo.agro2.br.api.dto.product.Status;
//import trevo.agro2.br.api.dto.user.RoleEnum;
//import trevo.agro2.br.api.dto.user.UserTokenService;
//import trevo.agro2.br.api.model.User;
//import trevo.agro2.br.api.repository.UserRepository;
//import trevo.agro2.br.api.security.TokenService;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@ActiveProfiles("test")
//@SpringBootTest(classes = ApiApplication.class)
//@AutoConfigureMockMvc
//public class UserAuthTest {
//    @Autowired
//    private UserRepository userRepository;
//    @Autowired
//    private MockMvc mockMvc;
//    @Autowired
//    private ObjectMapper objectMapper;
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//    @Autowired
//    private TokenService tokenService;
//    @Autowired
//    private AuthenticationManager manager;
//    @Autowired
//    private ProductRepository productRepository;
//
//    @Test
//    @DisplayName("Não deve permitir que o usuario cliente acesse o endpoint /user/register")
//    public void testClientAccess() throws Exception {
//        User user = new User();
//        user.setName("Kaique");
//        user.setLogin("kaique.q@outlook.com");
//        user.setPassword("123456789");
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        user.setRole(RoleEnum.CLIENTE);
//        userRepository.save(user);
//        String token = tokenService.token(user);
//        mockMvc.perform(post("/user/register")
//                        .header("Authorization", "Bearer " + token)
//                        .contentType(MediaType.APPLICATION_JSON_VALUE)
//                        .content(objectMapper.writeValueAsString(user)))
//                .andExpect(status().isForbidden());
//    }
//
//    @Test
//    @DisplayName("Deve permitir que o usuario administrador acesse o endpoint /user/register")
//    public void testAdminAccess() throws Exception {
//        User user = new User();
//        user.setName("Kaique");
//        user.setLogin("teste@teste.com");
//        user.setPassword("123456");
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        user.setRole(RoleEnum.ADMINISTRADOR);
//        userRepository.save(user);
//        String token = tokenService.token(user);
//        mockMvc.perform(post("/user/register")
//                        .header("Authorization", "Bearer " + token)
//                        .contentType(MediaType.APPLICATION_JSON_VALUE)
//                        .content(objectMapper.writeValueAsString(user)))
//                .andExpect(status().isBadRequest());
//    }
//
//    @Test
//    @DisplayName("Não deve permitir que o usuario colaborador acesse o endpoint /user/register")
//    public void testColaboratorAccess() throws Exception {
//        User user = new User();
//        user.setName("Kaique");
//        user.setLogin("kaique.q@hotmail.com");
//        user.setPassword("123456789");
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        user.setRole(RoleEnum.COLABORADOR);
//        userRepository.save(user);
//        String token = tokenService.token(user);
//        mockMvc.perform(post("/user/register")
//                        .header("Authorization", "Bearer " + token)
//                        .contentType(MediaType.APPLICATION_JSON_VALUE)
//                        .content(objectMapper.writeValueAsString(user)))
//                .andExpect(status().isForbidden());
//    }
//
//    @Test
//    public void testTokenAcess() throws Exception {
//        User user = new User();
//        user.setName("Kaique");
//        user.setLogin("kaique.q@gmail.com");
//        user.setPassword("123456789");
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        user.setRole(RoleEnum.COLABORADOR);
//        userRepository.save(user);
//        UserTokenService userTokenService = new UserTokenService(user.getLogin(), "123456789");
//        mockMvc.perform(post("/user/login")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(userTokenService)))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    @DisplayName("Deve permitir que um cliente liste os produtos")
//    public void testListProducts() throws Exception {
//        Product product = new Product();
//        product.setName("Teste");
//        product.setDescription("Teste");
//        product.setPrice(1923.5);
//        product.setCategory(Category.COMBUSTIVEL);
//        product.setStatus(Status.DISPONIVEL);
//        productRepository.save(product);
//        User user = new User();
//        user.setName("joao");
//        user.setLogin("joao.q@outlook.com");
//        user.setPassword("123456789");
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        user.setRole(RoleEnum.CLIENTE);
//        userRepository.save(user);
//        String token = tokenService.token(user);
//        mockMvc.perform(get("/product/list")
//                        .header("Authorization", "Bearer " + token)
//                        .contentType(MediaType.APPLICATION_JSON_VALUE)
//                        .content(objectMapper.writeValueAsString(user)))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    @DisplayName("Deve permitir que um colaborador liste os produtos")
//    public void testListProducts1() throws Exception {
//        Product product = new Product();
//        product.setName("Teste1");
//        product.setDescription("Teste");
//        product.setPrice(1923.5);
//        product.setCategory(Category.COMBUSTIVEL);
//        product.setStatus(Status.DISPONIVEL);
//        productRepository.save(product);
//        User user = new User();
//        user.setName("joao");
//        user.setLogin("joaoGabriel.q@outlook.com");
//        user.setPassword("123456789");
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        user.setRole(RoleEnum.COLABORADOR);
//        userRepository.save(user);
//        String token = tokenService.token(user);
//        mockMvc.perform(get("/product/list")
//                        .header("Authorization", "Bearer " + token)
//                        .contentType(MediaType.APPLICATION_JSON_VALUE)
//                        .content(objectMapper.writeValueAsString(user)))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    @DisplayName("Não deve permitir que um cliente exclua um produto")
//    public void testDeleteProducto_whenClient() throws Exception {
//        Product product = new Product();
//        product.setName("Tested1");
//        product.setDescription("Teste");
//        product.setPrice(1923.5);
//        product.setCategory(Category.COMBUSTIVEL);
//        product.setStatus(Status.DISPONIVEL);
//        productRepository.save(product);
//        User user = new User();
//        user.setName("joao");
//        user.setLogin("JoaoAntonio.q@outlook.com");
//        user.setPassword("123456789");
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        user.setRole(RoleEnum.CLIENTE);
//        userRepository.save(user);
//        String token = tokenService.token(user);
//        mockMvc.perform(delete("/product/delete/**")
//                        .header("Authorization", "Bearer " + token)
//                        .contentType(MediaType.APPLICATION_JSON_VALUE)
//                        .content(objectMapper.writeValueAsString(user)))
//                .andExpect(status().isForbidden());
//    }
//
//    @Test
//    @DisplayName("Deve permitir que um colaborador exclua um produto")
//    public void testDeleteProducto_whenColaborator() throws Exception {
//        Product product = new Product();
//        product.setName("Teste2");
//        product.setDescription("Teste");
//        product.setPrice(1923.5);
//        product.setCategory(Category.COMBUSTIVEL);
//        product.setStatus(Status.DISPONIVEL);
//        productRepository.save(product);
//        User user = new User();
//        user.setName("joao");
//        user.setLogin("joaoGabl.q@outlook.com");
//        user.setPassword("123456789");
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        user.setRole(RoleEnum.COLABORADOR);
//        userRepository.save(user);
//        String token = tokenService.token(user);
//        mockMvc.perform(delete("/product/delete/" + product.getId())
//                        .header("Authorization", "Bearer " + token)
//                        .contentType(MediaType.APPLICATION_JSON_VALUE))
//                .andExpect(status().isOk());
//    }
//}
