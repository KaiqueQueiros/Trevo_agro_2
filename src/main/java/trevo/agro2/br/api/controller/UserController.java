package trevo.agro2.br.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import trevo.agro2.br.api.dto.user.UserTokenService;
import trevo.agro2.br.api.model.User;
import trevo.agro2.br.api.service.UserService;

import java.util.UUID;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;


    @PostMapping(value = "/register")
    @Secured("ROLE_ADMINISTRADOR")
    @Operation(summary = "Cadastra um novo usuario na api",tags = "User",security = { @SecurityRequirement(name = "bearer-key") })
    public ResponseEntity<?> register(@RequestBody @Valid User user) {
        return userService.register(user);
    }

    @GetMapping(value = "/list")
    @Operation(summary = "Lista os usuarios da api",tags = "User",security = { @SecurityRequirement(name = "bearer-key") })
    public ResponseEntity<?> list() {
        return userService.list();
    }

    @PostMapping(value = "/login")
    @Operation(summary = "Login na api e fornecendo o JTW",tags = "User")
    public ResponseEntity<?> login(@RequestBody @Valid UserTokenService dto) {
        return userService.token(dto);
    }

    @PutMapping(value = "/update/{id}")
    @Operation(summary = "Atualiza os dados do usuario",tags = "User")
    public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody @Valid User user) {
        return userService.update(id, user);
    }
    @DeleteMapping(value = "/delete/{id}")
    @Secured("ROLE_ADMINISTRADOR")
    @Operation(summary = "Deleta um usuario da api",tags = "User")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        return userService.delete(id);
    }
}
