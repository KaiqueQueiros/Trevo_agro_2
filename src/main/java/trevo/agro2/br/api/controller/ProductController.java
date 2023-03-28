package trevo.agro2.br.api.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import trevo.agro2.br.api.dto.ProductDto;
import trevo.agro2.br.api.service.ProductService;

import java.util.UUID;

@RestController
@RequestMapping("product")
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> register(@RequestBody @Valid ProductDto dto) {
        return productService.register(dto);
    }

    @GetMapping(value = "/list")
    public ResponseEntity<?> list() {
        return productService.list();
    }
    @GetMapping(value = "/find/{id}")
    public ResponseEntity<?> find(@PathVariable UUID id) {
        return productService.findByName(id);
    }
    @DeleteMapping(value = "delete/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        return productService.delete(id);
    }
    @PutMapping(value = "/update/{id}")
    public ResponseEntity<?> update(@PathVariable UUID id,@RequestBody ProductDto dto) {
        return productService.update(dto,id);
    }
}
