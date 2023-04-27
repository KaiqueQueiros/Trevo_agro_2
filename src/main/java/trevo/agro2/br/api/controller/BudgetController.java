package trevo.agro2.br.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import trevo.agro2.br.api.dto.budget.BudgetDTO;
import trevo.agro2.br.api.service.BudgetService;
import java.util.UUID;

@RestController
@RequestMapping("budget")
@SecurityRequirement(name = "bearer-key")
public class BudgetController {
    @Autowired
    private BudgetService service;

    @PostMapping(value = "/register")
    @Operation(summary = "Cadastra um novo orçamento",tags = "Budget")
    public ResponseEntity<?> register(@RequestBody @Valid BudgetDTO dto) {
        return service.register(dto);
    }

    @GetMapping(value = "/list")
    @Operation(summary = "Lista todos os orçamentos",tags = "Budget")
    public ResponseEntity<?> list() {
        return service.list();
    }

    @DeleteMapping(value = "/delete/{id}")
    @Operation(summary = "Deleta orçamentos de clientes",tags = "Budget")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        return service.delete(id);
    }

    @GetMapping(value = "/find/id/{id}")
    @Operation(summary = "Filtra orçamentos pelo id",tags = "Budget")
    public ResponseEntity<?> detailsClientId(@PathVariable UUID id) {
        return service.detailsId(id);
    }

    @GetMapping(value = "/find/email/{email}")
    @Operation(summary = "Filtra orçamentos pelo email",tags = "Budget")
    public ResponseEntity<?> detailsClientEmail(@PathVariable String email) {
        return service.detailsEmail(email);
    }

    @PutMapping(value = "/update/{id}")
    @Operation(summary = "Atualiza orçamentos pelo id",tags = "Budget")
    public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody @Valid BudgetDTO dto) {
        return service.update(dto, id);
    }
}
