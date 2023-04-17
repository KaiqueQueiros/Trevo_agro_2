package trevo.agro2.br.api.product;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import trevo.agro2.br.api.ApiApplication;
import trevo.agro2.br.api.dto.product.Category;
import trevo.agro2.br.api.dto.product.Status;
import trevo.agro2.br.api.model.Product;
import trevo.agro2.br.api.repository.ProductRepository;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
@SpringBootTest(classes = ApiApplication.class)
@AutoConfigureMockMvc
public class ProductRepositoryTest {
    UUID id = UUID.randomUUID();
    @Autowired
    private ProductRepository productRepository;

    @Test
    public void whenCreate_thenPersistenceDate() {
        Product product = new Product();
        product.setName("Maquina agricula");
        product.setDescription("Pulverizador");
        product.setPrice(1923.5);
        product.setCategory(Category.COMBUSTIVEL);
        product.setStatus(Status.DISPONIVEL);

        Product save = productRepository.save(product);
        Assertions.assertThat(save.getId()).isNotNull();
        assertThat(product.getName()).isEqualTo("Maquina agricula");
        assertThat(product.getDescription()).isEqualTo("Pulverizador");
        assertThat(product.getPrice()).isEqualTo(1923.5);
        assertThat(product.getCategory()).isEqualTo(Category.COMBUSTIVEL);
        assertThat(product.getStatus()).isEqualTo(Status.DISPONIVEL);
    }

    @Test
    public void whenFindProduct() {
        Product product = new Product();
        product.setName("Maquina");
        product.setDescription("Pulverizador");
        product.setPrice(1923.5);
        product.setCategory(Category.COMBUSTIVEL);
        product.setStatus(Status.DISPONIVEL);

        productRepository.save(product);
        Optional<Product> byId = productRepository.findById(product.getId());
        assertFalse(byId.isEmpty());
    }

    @Test
    public void whenDeleteProduct() {
        Product product = new Product();
        product.setName("Maquina ");
        product.setDescription("Pulverizador");
        product.setPrice(1923.5);
        product.setCategory(Category.COMBUSTIVEL);
        product.setStatus(Status.DISPONIVEL);

        Product save = productRepository.save(product);
        Optional<Product> byId = productRepository.findById(product.getId());
        productRepository.deleteById(product.getId());
        assertFalse(byId.isEmpty());
    }
}
