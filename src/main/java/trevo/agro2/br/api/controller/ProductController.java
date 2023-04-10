package trevo.agro2.br.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import trevo.agro2.br.api.dto.product.ProductDto;
import trevo.agro2.br.api.service.ProductService;

import java.util.UUID;

@RestController
@RequestMapping("product")
@SecurityRequirement(name = "bearer-key")
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Cadastra um novo produto", tags = {"Product"})
    public ResponseEntity<?> register(@RequestBody @Valid ProductDto dto) {
        return productService.register(dto);
    }

    @GetMapping(value = "/list")
    @Operation(summary = "Lista todos os produtos cadastrados", tags = {"Product"})
    public ResponseEntity<?> list() {
        return productService.list();
    }

    @GetMapping(value = "/find/{id}")
    @Operation(summary = "Busca detalhada de um produto", tags = {"Product"})
    public ResponseEntity<?> find(@PathVariable UUID id) {
        return productService.findByName(id);
    }

    @DeleteMapping(value = "/delete/{id}")
    @Operation(summary = "Exclui um produto pelo identificador", tags = {"Product"})
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        return productService.delete(id);
    }

    @PutMapping(value = "/update/{id}")
    @Operation(summary = "Atualiza um produto pelo seu identificador", tags = {"Product"})
    public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody ProductDto dto) {
        return productService.update(dto, id);
    }
}
