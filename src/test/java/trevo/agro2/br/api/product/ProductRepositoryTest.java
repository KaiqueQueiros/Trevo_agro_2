package trevo.agro2.br.api.product;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import trevo.agro2.br.api.dto.ProductDto;
import trevo.agro2.br.api.model.Product;
import trevo.agro2.br.api.repository.ProductRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductRepositoryTest {
    @Autowired
    ProductRepository productRepository;

    @Test
    public void whenCreate_thenPersistenceDate() {
        Product product = new Product(new ProductDto("Apagando", "Testando aqui", 197.2, null, null));
        productRepository.save(product);
        Assertions.assertThat(product.getId()).isNotNull();
        assertThat(product.getName()).isEqualTo("Apagando");
    }
    @Test
    public void whenFindProduct() {
        Product product = new Product(new ProductDto("Apagando", "Testando aqui", 197.2, null, null));
        productRepository.save(product);
        Optional<Product> byId = productRepository.findById(product.getId());
        assertTrue(byId.isPresent());

    }

}
