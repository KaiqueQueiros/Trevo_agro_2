package trevo.agro2.br.api.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(description = "Nome de um usuario", example = "Nome do usuario cadastrado")
    String name;
    @Schema(description = "Email de acesso de um usuario", example = "O email do usuario cadastrado servira para que o mesmo possa fazer login na api")
    String login;
    @Schema(description = "Data de criação do usuario", example = "Data em que o usuario foi cadastrado")
    String date;
    @Schema(description = "Permissão de acesso que o usuario possui", example = "Tipo de acesso que o usuario possui sendo eles ADMINISTRAODR, COLABORADOR ou CLIENTE")
    RoleEnum role;

    public UserDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.login = user.getLogin();
        this.date = user.getDate();
        this.role = user.getRole();
    }
}
