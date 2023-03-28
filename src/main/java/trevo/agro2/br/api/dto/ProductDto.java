package trevo.agro2.br.api.dto;

import jakarta.validation.constraints.NotEmpty;

public record ProductDto(
        @NotEmpty(message = "Necessario informar o nome do produto")
        String name,
        @NotEmpty(message = "Necessario informar a descrição do produto")
        String description,
//        @NotEmpty(message = "Necessario informar o preço do produto")
        Double price,
        Status status

) {
}
