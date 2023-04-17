package trevo.agro2.br.api.service;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import trevo.agro2.br.api.dto.user.UserDto;
import trevo.agro2.br.api.dto.user.UserTokenService;
import trevo.agro2.br.api.exceptions.models.BadRequestException;
import trevo.agro2.br.api.model.User;
import trevo.agro2.br.api.repository.UserRepository;
import trevo.agro2.br.api.security.TokenService;
import trevo.agro2.br.api.utils.ResponseModelMessage;
import trevo.agro2.br.api.utils.ResponseModelObject;
import trevo.agro2.br.api.utils.ResponseModelToken;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager manager;
    @Autowired
    TokenService tokenService;

    public ResponseEntity<?> register(@RequestBody @Valid User user) {
        if (userRepository.existsByLogin(user.getLogin())) {
            throw new BadRequestException("Email já em uso");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return new ResponseEntity<>(new ResponseModelObject("Usuario " + user.getName() + " cadastrado", user), HttpStatus.CREATED);
    }

    public ResponseEntity<?> list() {
        List<User> userList = userRepository.findAll();
        if (userList.isEmpty()) {
            throw new BadRequestException("Nenhum usuario encontrado");
        }
        List<UserDto> dtoList = new ArrayList<>();
        for (User user : userList) {
            UserDto userDto = new UserDto(user);
            dtoList.add(userDto);
        }
        return new ResponseEntity<>(new ResponseModelObject("Lista de usuarios", dtoList), HttpStatus.OK);
    }
    public ResponseEntity<?> findUser(@PathVariable Long id){
        if (!userRepository.existsById(id)){
            throw new BadRequestException("Usuario não encontrado");
        }
        User user = userRepository.findById(id).orElse(null);
        return new ResponseEntity<>(new ResponseModelObject("Detalhes do usuario de id : " + id,user),HttpStatus.OK);
    }

    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody @Valid User dto) {
        User user = userRepository.findById(id).orElse(null);
        if (!userRepository.existsById(id) || user == null) {
            throw new BadRequestException("Usuario não encontrado");
        }
        if (userRepository.existsByLogin(dto.getLogin())) {
            throw new BadRequestException("Email já em uso");
        }
        user.update(dto);
        if (dto.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(dto.getPassword()));
        }
        userRepository.save(user);
        return new ResponseEntity<>(new ResponseModelObject("Usuario " + user.getName() + " atualizado", user), HttpStatus.OK);
    }

    public ResponseEntity<?> delete(@PathVariable Long id) {
        if (!userRepository.existsById(id)) {
            throw new BadRequestException("Usuario inexistente");
        }
        userRepository.deleteById(id);
        return new ResponseEntity<>(new ResponseModelMessage("Usuario excluido"), HttpStatus.OK);
    }

    public ResponseEntity<?>  token(@RequestBody @Valid UserTokenService dto) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(dto.login(), dto.password());
        Authentication auth = manager.authenticate(token);
        return new ResponseEntity<>(new ResponseModelToken(tokenService.token((User) auth.getPrincipal())), HttpStatus.OK);
    }


}
