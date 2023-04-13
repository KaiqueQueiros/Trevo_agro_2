package trevo.agro2.br.api.product;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import trevo.agro2.br.api.dto.product.Category;
import trevo.agro2.br.api.dto.product.ProductDto;
import trevo.agro2.br.api.dto.product.Status;
import trevo.agro2.br.api.model.Product;
import trevo.agro2.br.api.repository.ProductRepository;
import java.util.Optional;
import java.util.UUID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class ProductRepositoryTest {
    UUID id = UUID.randomUUID();
    public Product product = new Product(new ProductDto(id,"Condorito", "Testando",
            197.2, Status.DISPONIVEL, Category.COMBUSTIVEL));
    @Autowired
    private ProductRepository productRepository;

    @Test
    public void whenCreate_thenPersistenceDate() {
        productRepository.save(product);
        Assertions.assertThat(product.getId()).isNotNull();
        assertThat(product.getName()).isEqualTo("Condorito");
    }

    @Test
    public void whenFindProduct() {
        productRepository.save(product);
        Optional<Product> byId = productRepository.findById(product.getId());
        assertFalse(byId.isEmpty());
    }

    @Test
    public void whenDeleteProduct() {
        productRepository.save(product);
        Optional<Product> byId = productRepository.findById(product.getId());
        productRepository.deleteById(product.getId());
        assertFalse(byId.isEmpty());
    }
}
