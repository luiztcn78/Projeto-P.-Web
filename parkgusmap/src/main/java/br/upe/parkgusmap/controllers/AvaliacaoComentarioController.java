package br.upe.parkgusmap.controllers;

import br.upe.parkgusmap.entities.Avaliacao;
import br.upe.parkgusmap.entities.Comentario;
import br.upe.parkgusmap.entities.DTOs.AvaliacaoDTO;
import br.upe.parkgusmap.entities.DTOs.ComentarioDTO;
import br.upe.parkgusmap.services.AvaliacaoService;
import br.upe.parkgusmap.services.ComentarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/relacionamentos")
@RequiredArgsConstructor
public class AvaliacaoComentarioController {

    @Autowired
    private final AvaliacaoService avaliacaoService;
    @Autowired
    private final ComentarioService comentarioService;

    @PostMapping("/avaliacoes")
    public ResponseEntity<AvaliacaoDTO> criarAvaliacao(@RequestParam Long avaliadorId,
                                                       @RequestParam Long localId,
                                                       @RequestParam int nota) {
        try {
            Avaliacao avaliacao = avaliacaoService.criarAvaliacao(avaliadorId, localId, nota);
            return ResponseEntity.ok(new AvaliacaoDTO(avaliacao));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/avaliacoes/usuario/{usuarioId}")
    public List<AvaliacaoDTO> getAvaliacoesPorUsuario(@PathVariable Long usuarioId) {
        return avaliacaoService.findByUsuarioId(usuarioId)
                .stream()
                .map(AvaliacaoDTO::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/avaliacoes/local/{localId}")
    public List<AvaliacaoDTO> getAvaliacoesPorLocal(@PathVariable Long localId) {
        return avaliacaoService.findByLocalId(localId)
                .stream()
                .map(AvaliacaoDTO::new)
                .collect(Collectors.toList());
    }

    @PostMapping("/comentarios")
    public ResponseEntity<ComentarioDTO> criarComentario(@RequestBody ComentarioDTO comentarioDTO) {
        try {
            Comentario comentario = comentarioService.criarComentario(
                comentarioDTO.getUsuarioId(), 
                comentarioDTO.getLocalId(), 
                comentarioDTO.getTexto()
            );
            return ResponseEntity.ok(new ComentarioDTO(comentario));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/comentarios/usuario/{usuarioId}")
    public List<ComentarioDTO> getComentariosPorUsuario(@PathVariable Long usuarioId) {
        return comentarioService.findByUsuarioId(usuarioId)
                .stream()
                .map(ComentarioDTO::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/comentarios/local/{localId}")
    public List<ComentarioDTO> getComentariosPorLocal(@PathVariable Long localId) {
        return comentarioService.findByLocalId(localId)
                .stream()
                .map(ComentarioDTO::new)
                .collect(Collectors.toList());
    }
}