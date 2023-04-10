package trevo.agro2.br.api.dto.product;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import trevo.agro2.br.api.dto.product.Category;
import trevo.agro2.br.api.dto.product.Status;

public record ProductDto(
        @NotEmpty(message = "Necessario informar o nome do produto")
        @Schema(description = "Nome de um produto",example = "Nome de maquinas que a trevo produz")
        String name,
        @NotEmpty(message = "Necessario informar a descrição do produto")
        @Schema(description = "Descrição do produto",example = "Descrição completa do produto")
        String description,
        @Schema(description = "Precificação do produto",example = "19999.99")
        Double price,
        @Schema(description = "Status atual do produto sera setado na criação como DISPONIVEL",example = "DISPONIVEL,INDISPONIVEL,FORADELINHA")
        Status status,
        @Schema(description = "Categoria do produto",example = "MANUAIS,ELETRICOS,COMBUSTIVEL")
        Category category
) {
}
