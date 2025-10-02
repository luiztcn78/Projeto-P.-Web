package br.upe.parkgusmap.controllers;

import br.upe.parkgusmap.entities.Avaliacao;
import br.upe.parkgusmap.entities.Comentario;
import br.upe.parkgusmap.services.AvaliacaoService;
import br.upe.parkgusmap.services.ComentarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/relacionamentos")
@RequiredArgsConstructor
public class AvaliacaoComentarioController {

    private final AvaliacaoService avaliacaoService;
    private final ComentarioService comentarioService;

    // Endpoints para Avaliações
    @PostMapping("/avaliacoes")
    public ResponseEntity<Avaliacao> criarAvaliacao(@RequestParam Long avaliadorId, 
                                                   @RequestParam Long localId, 
                                                   @RequestParam int nota) {
        try {
            Avaliacao avaliacao = avaliacaoService.criarAvaliacao(avaliadorId, localId, nota);
            return ResponseEntity.ok(avaliacao);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/avaliacoes/usuario/{usuarioId}")
    public ResponseEntity<List<Avaliacao>> getAvaliacoesPorUsuario(@PathVariable Long usuarioId) {
        try {
            List<Avaliacao> avaliacoes = avaliacaoService.findByUsuarioId(usuarioId);
            return ResponseEntity.ok(avaliacoes);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/avaliacoes/local/{localId}")
    public ResponseEntity<List<Avaliacao>> getAvaliacoesPorLocal(@PathVariable Long localId) {
        try {
            List<Avaliacao> avaliacoes = avaliacaoService.findByLocalId(localId);
            return ResponseEntity.ok(avaliacoes);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Endpoints para Comentários
    @PostMapping("/comentarios")
    public ResponseEntity<Comentario> criarComentario(@RequestParam Long avaliadorId, 
                                                     @RequestParam Long localId, 
                                                     @RequestParam String texto) {
        try {
            Comentario comentario = comentarioService.criarComentario(avaliadorId, localId, texto);
            return ResponseEntity.ok(comentario);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/comentarios/usuario/{usuarioId}")
    public ResponseEntity<List<Comentario>> getComentariosPorUsuario(@PathVariable Long usuarioId) {
        try {
            List<Comentario> comentarios = comentarioService.findByUsuarioId(usuarioId);
            return ResponseEntity.ok(comentarios);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/comentarios/local/{localId}")
    public ResponseEntity<List<Comentario>> getComentariosPorLocal(@PathVariable Long localId) {
        try {
            List<Comentario> comentarios = comentarioService.findByLocalId(localId);
            return ResponseEntity.ok(comentarios);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}