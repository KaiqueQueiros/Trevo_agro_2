package user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import trevo.agro2.br.api.dto.user.RoleEnum;
import trevo.agro2.br.api.model.User;
import trevo.agro2.br.api.repository.UserRepository;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertFalse;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    public User user = new User("Kaique", "Kaique", "123456", RoleEnum.ADMINISTRADOR);

//    @Test
//    public void whenListAllUsers() {
//        userRepository.save(user);
//        List<User> all = userRepository.findAll();
//        assertFalse(all.isEmpty());
//    }
}
