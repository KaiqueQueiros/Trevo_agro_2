package trevo.agro2.br.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import trevo.agro2.br.api.model.Chamado;

import java.util.UUID;

@Repository
public interface ChamadoRespository extends JpaRepository<Chamado, UUID> {
}
