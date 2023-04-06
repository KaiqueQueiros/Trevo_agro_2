package trevo.agro2.br.api.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import trevo.agro2.br.api.dto.UserTokenService;
import trevo.agro2.br.api.model.User;
import trevo.agro2.br.api.service.UserService;
import java.util.UUID;

@RestController
@RequestMapping(value = "/login")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<?> register(@RequestBody @Valid User user) {
        return userService.register(user);
    }

    @GetMapping(value = "/list")
    public ResponseEntity<?> list() {
        return userService.list();
    }

    @GetMapping(value = "/validpassword")
    public ResponseEntity<?> validPassword(@RequestParam String login,
                                           @RequestParam String password) {
        return userService.validPassword(login, password);
    }

    @PostMapping(value = "/teste")
    public ResponseEntity<?> teste(@RequestBody @Valid UserTokenService dto) {
        return userService.token(dto);
    }

    //    @PutMapping(value = "/update/{id}")
//    public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody @Valid User user) {
//        return userService.update(id, user);
//    }
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        return userService.delete(id);
    }
}
