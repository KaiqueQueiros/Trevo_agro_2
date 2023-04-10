package trevo.agro2.br.api.dto.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import trevo.agro2.br.api.model.User;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {
    UUID id;
    String name;
    String login;
    String date;

    public UserDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.login = user.getLogin();
        this.date = user.getDate();
    }
}
