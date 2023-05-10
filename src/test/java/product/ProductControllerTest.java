package product;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import trevo.agro2.br.api.ApiApplication;
import trevo.agro2.br.api.dto.product.Category;
import trevo.agro2.br.api.dto.product.ProductDto;
import trevo.agro2.br.api.dto.product.Status;
import trevo.agro2.br.api.dto.user.RoleEnum;
import trevo.agro2.br.api.model.User;
import trevo.agro2.br.api.repository.UserRepository;
import trevo.agro2.br.api.security.TokenService;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
@SpringBootTest(classes = ApiApplication.class)
@AutoConfigureMockMvc
public class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("Deve criar um novo produto com base no endpoint product/register")
    public void whenCreateNewProduct_thenProductIsCreated() throws Exception {
        User user = new User();
        user.setName("joao");
        user.setLogin("jorlan.q@outlook.com");
        user.setPassword("123456789");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(RoleEnum.COLABORADOR);
        userRepository.save(user);
        String token = tokenService.token(user);
        ProductDto productDto = new ProductDto("Condor", "Testando alteração", 1923.5, Status.DISPONIVEL, Category.ELETRICOS);
        mockMvc.perform(post("/product/register")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(productDto))
                )
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Deve validar se o endpoint esta permitindo cadastrar dois produtos com o mesmo nome")
    public void whenCreateNewProduct_andValidateDuplicateName() throws Exception {
        User user = new User();
        user.setName("joao");
        user.setLogin("kewry.q@outlook.com");
        user.setPassword("123456789");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(RoleEnum.COLABORADOR);
        userRepository.save(user);
        String token = tokenService.token(user);
        ProductDto productDto = new ProductDto("Trator", "Testando alteração", 1923.5, Status.DISPONIVEL, Category.ELETRICOS);
        ProductDto productDto2 = new ProductDto("Trator", "Testando alteração", 1923.5, Status.DISPONIVEL, Category.ELETRICOS);
        mockMvc.perform(post("/product/register")
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(productDto)
                )).andExpect(status().isCreated());
        mockMvc.perform(post("/product/register")
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(productDto2))
        ).andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Deve listar produtos cadastrados")
    public void whenListAllProducts() throws Exception {
        User user = new User();
        user.setName("joao");
        user.setLogin("Adan.q@outlook.com");
        user.setPassword("123456789");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(RoleEnum.COLABORADOR);
        userRepository.save(user);
        String token = tokenService.token(user);
        ProductDto productDto = new ProductDto("Trator1", "Testando alteração", 1923.5, Status.DISPONIVEL, Category.ELETRICOS);
        ProductDto productDto2 = new ProductDto("Trator2", "Testando alteração", 1923.5, Status.DISPONIVEL, Category.ELETRICOS);
        mockMvc.perform(post("/product/register")
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(productDto)
                )).andExpect(status().isCreated());
        mockMvc.perform(post("/product/register")
                .header("Authorization", "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(productDto2))
        ).andExpect(status().isCreated());
        mockMvc.perform(get("/product/list")
                .header("Authorization", "Bearer " + token)
        ).andExpect(status().isOk());
    }


}
