package trevo.agro2.br.api.dto;

public record ChamadoDTO(
        String imagem,
        String descricao,
        Prioridade prioridade,
        String latitude,
        String longitude
) {
}
