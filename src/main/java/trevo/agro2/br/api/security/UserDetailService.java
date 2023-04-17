package trevo.agro2.br.api.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import trevo.agro2.br.api.exceptions.models.BadRequestException;
import trevo.agro2.br.api.repository.UserRepository;

@Service
public class UserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (!userRepository.existsByLogin(username)) {
            throw new BadRequestException("Usuário inexistente ou senha inválida");
        }
        return userRepository.findByLogin(username);
    }
}
