package trevo.agro2.br.api.dto.budget;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record BudgetDTO(
        @NotEmpty(message = "Campo nome é obrigatorio")
        String name,
        @Email(message = "Informe um email valido")
        @NotEmpty(message = "Informe o email")
        String email,
        @NotEmpty(message = "Informe seu telefone")
        String phone,
        @NotEmpty(message = "Informe qual seu país")
        String country,
        @NotEmpty(message = "Informe o nome de sua empresa")
        String company,
        @JsonProperty("products")
        @NotEmpty(message = "É necessário informar pelo menos um produto de interesse")
        List<Long> productIds
) {
}
