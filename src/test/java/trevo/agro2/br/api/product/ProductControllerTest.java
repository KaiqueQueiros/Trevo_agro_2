package trevo.agro2.br.api.product;

import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import trevo.agro2.br.api.controller.ProductController;
import trevo.agro2.br.api.repository.ProductRepository;
import trevo.agro2.br.api.service.ProductService;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.when;

//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@ActiveProfiles("test")
//@MockitoSettings
@WebMvcTest
public class ProductControllerTest {

    @Autowired
    private ProductController productController;
    @Autowired
    private ProductRepository productRepository;
    @InjectMocks
    ProductService productService;

    @BeforeEach
    public void setup() {
        RestAssuredMockMvc.standaloneSetup(this.productController);
    }

//    @Test
//    public void whenReturnSucess() {
//        given()
//                .accept(ContentType.JSON)
//                .when()
//                .get("/list")
//                .then().statusCode(HttpStatus.OK.value());
//    }
}
