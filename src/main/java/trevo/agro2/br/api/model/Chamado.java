package trevo.agro2.br.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import trevo.agro2.br.api.dto.ChamadoDTO;
import trevo.agro2.br.api.dto.Prioridade;

import java.util.UUID;

@Entity
@Table(name = "tb_chamado")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Chamado {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String imagem;
    private String descricao;
    private Prioridade prioridade;
    private String latitude;
    private String longitude;


    public Chamado(ChamadoDTO dto) {
        this.imagem = dto.imagem();
        this.descricao = dto.descricao();
        this.prioridade = dto.prioridade();
        this.latitude = dto.latitude();
        this.longitude = dto.longitude();
    }

}
