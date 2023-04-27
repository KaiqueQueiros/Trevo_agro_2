package trevo.agro2.br.api.dto.budget;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;
import java.util.UUID;

public record BudgetDTO(
        @NotEmpty(message = "Campo nome é obrigatorio")
        @Schema(description = "Nome do cliente", example = "Nome do cliente que faz o pedido")
        String name,
        @Email(message = "Informe um email valido")
        @NotEmpty(message = "Informe o email")
        @Schema(description = "Email de contato do cliente", example = "Email para que possa ser possivel o vendedor entrar em contato")
        String email,
        @Schema(description = "Telefone de contato do cliente", example = "Telefone para que possa ser possivel o vendedor entrar em contato")
        @NotEmpty(message = "Informe seu telefone")
        String phone,
        @NotEmpty(message = "Informe qual seu país")
        @Schema(description = "Localidade do cliente", example = "Para que possa ser possivel o vendedor faça um contato mais acertivo com o cliente")
        String country,
        @NotEmpty(message = "Informe o nome de sua empresa")
        @Schema(description = "Empresa em que o cliente representa", example = "Nome da empresa do cliente para que seja possivel que o vendedor saiba mais sobre qual a necessidade do cliente")
        String company,
        @JsonProperty("products")
        @NotEmpty(message = "É necessário informar pelo menos um produto de interesse")
        @Schema(description = "Lista de produtos de interesse que o cliente possui", example = "Para que o cliente possa fazer requisição de mais de um produto em um unico pedido")
        List<UUID> productIds
) {
}
