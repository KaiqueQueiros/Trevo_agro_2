package trevo.agro2.br.api.dto;

import jakarta.validation.constraints.Email;

public record UserTokenService(
        @Email(message = "Informe seu email de acesso")
        String login,
        String password
) {
}
