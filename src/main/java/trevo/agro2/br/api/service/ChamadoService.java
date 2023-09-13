package trevo.agro2.br.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import trevo.agro2.br.api.dto.ChamadoDTO;
import trevo.agro2.br.api.model.Chamado;
import trevo.agro2.br.api.repository.ChamadoRespository;

import java.util.List;
import java.util.UUID;

@Service
public class ChamadoService {

    @Autowired
    private ChamadoRespository chamadoRespository;

    public ResponseEntity<?> criaChamado(@RequestBody ChamadoDTO dto) {
        Chamado chamado = new Chamado(dto);
        chamadoRespository.save(chamado);
        return ResponseEntity.ok(chamado);
    }

    public ResponseEntity<?> buscaChamado(UUID id) {
        Chamado chamado = chamadoRespository.findById(id).orElse(null);
        return ResponseEntity.ok(chamado);
    }

    public ResponseEntity<?> lista() {
        List<Chamado>  chamados = chamadoRespository.findAll();
        return ResponseEntity.ok(chamados);
    }
}
