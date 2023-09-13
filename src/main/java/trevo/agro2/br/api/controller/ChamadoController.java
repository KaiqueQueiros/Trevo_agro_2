package trevo.agro2.br.api.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import trevo.agro2.br.api.dto.ChamadoDTO;
import trevo.agro2.br.api.service.ChamadoService;

import java.util.UUID;

@RestController
@RequestMapping(value = "chamado")
public class ChamadoController {
    @Autowired
    ChamadoService chamadoService;

    @PostMapping(value = "/criar")
    public ResponseEntity<?> criaChamado(@RequestBody @Valid ChamadoDTO dto) {
        return chamadoService.criaChamado(dto);
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<?> buscaChamado(@PathVariable UUID id) {
        return chamadoService.buscaChamado(id);
    }
    @GetMapping(value = "/listar")
    public ResponseEntity<?> lista() {
        return chamadoService.lista();
    }


}
