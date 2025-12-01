package br.upe.parkgusmap.controllers;

import br.upe.parkgusmap.entities.Avaliacao;
import br.upe.parkgusmap.entities.Comentario;
import br.upe.parkgusmap.entities.DTOs.AvaliacaoDTO;
import br.upe.parkgusmap.entities.DTOs.ComentarioDTO;
import br.upe.parkgusmap.services.AvaliacaoService;
import br.upe.parkgusmap.services.ComentarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    // ========== AVALIAÇÕES ==========

    @GetMapping("/avaliacoes")
    public List<AvaliacaoDTO> getAllAvaliacoes() {
        return avaliacaoService.findAll().stream()
                .map(AvaliacaoDTO::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/avaliacoes/{id}")
    public ResponseEntity<AvaliacaoDTO> getAvaliacaoById(@PathVariable Long id) {
        Avaliacao avaliacao = avaliacaoService.findById(id);
        AvaliacaoDTO avaliacaoDTO = new AvaliacaoDTO(avaliacao);
        return ResponseEntity.ok(avaliacaoDTO);
    }

    @PostMapping("/avaliacoes")
    public ResponseEntity<AvaliacaoDTO> criarAvaliacao(@RequestParam Long avaliadorId,
                                                       @RequestParam Long localId,
                                                       @RequestParam int nota) {
        Avaliacao avaliacao = avaliacaoService.criarAvaliacao(avaliadorId, localId, nota);
        return ResponseEntity.ok(new AvaliacaoDTO(avaliacao));
    }

    @PutMapping("/avaliacoes/{id}")
    public ResponseEntity<AvaliacaoDTO> updateAvaliacao(@PathVariable Long id, 
                                                       @RequestParam int novaNota) {
        Avaliacao avaliacaoExistente = avaliacaoService.findById(id);
        
        Avaliacao avaliacaoAtualizada = new Avaliacao();
        avaliacaoAtualizada.setId(id);
        avaliacaoAtualizada.setNota(novaNota);
        avaliacaoAtualizada.setUsuario(avaliacaoExistente.getUsuario());
        avaliacaoAtualizada.setLocal(avaliacaoExistente.getLocal());
        
        Avaliacao updatedAvaliacao = avaliacaoService.update(id, avaliacaoAtualizada);
        return ResponseEntity.ok(new AvaliacaoDTO(updatedAvaliacao));
    }

    @DeleteMapping("/avaliacoes/{id}")
    public ResponseEntity<Void> deleteAvaliacao(@PathVariable Long id) {
        avaliacaoService.deleteById(id);
        return ResponseEntity.noContent().build();
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

    @GetMapping("/avaliacoes/usuario/{usuarioId}/local/{localId}")
    public ResponseEntity<AvaliacaoDTO> getAvaliacaoUsuarioLocal(@PathVariable Long usuarioId, 
                                                               @PathVariable Long localId) {
        List<Avaliacao> avaliacoes = avaliacaoService.findByUsuarioIdAndLocalId(usuarioId, localId);
        if (!avaliacoes.isEmpty()) {
            return ResponseEntity.ok(new AvaliacaoDTO(avaliacoes.get(0)));
        }
        return ResponseEntity.notFound().build();
    }

    // ========== COMENTÁRIOS ==========

    @GetMapping("/comentarios")
    public List<ComentarioDTO> getAllComentarios() {
        return comentarioService.findAll().stream()
                .map(ComentarioDTO::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/comentarios/{id}")
    public ResponseEntity<ComentarioDTO> getComentarioById(@PathVariable Long id) {
        Comentario comentario = comentarioService.findById(id);
        ComentarioDTO comentarioDTO = new ComentarioDTO(comentario);
        return ResponseEntity.ok(comentarioDTO);
    }

    @PostMapping("/comentarios")
    public ResponseEntity<ComentarioDTO> criarComentario(@RequestBody ComentarioDTO comentarioDTO) {
        Comentario comentario = comentarioService.criarComentario(
                comentarioDTO.getUsuarioId(),
                comentarioDTO.getLocalId(), 
                comentarioDTO.getTexto()
            );
        return ResponseEntity.ok(new ComentarioDTO(comentario));
    }

    @PutMapping("/comentarios/{id}")
    public ResponseEntity<ComentarioDTO> updateComentario(@PathVariable Long id, 
                                                         @RequestBody ComentarioDTO comentarioDTO) {
        Comentario comentarioExistente = comentarioService.findById(id);
        
        Comentario comentarioAtualizado = new Comentario();
        comentarioAtualizado.setId(id);
        comentarioAtualizado.setTexto(comentarioDTO.getTexto());
        comentarioAtualizado.setUsuario(comentarioExistente.getUsuario());
        comentarioAtualizado.setLocal(comentarioExistente.getLocal());
        
        Comentario updatedComentario = comentarioService.update(id, comentarioAtualizado);
        return ResponseEntity.ok(new ComentarioDTO(updatedComentario));
    }

    @DeleteMapping("/comentarios/{id}")
    public ResponseEntity<Void> deleteComentario(@PathVariable Long id) {
        comentarioService.deleteById(id);
        return ResponseEntity.noContent().build();
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

    @GetMapping("/comentarios/usuario/{usuarioId}/local/{localId}")
    public ResponseEntity<ComentarioDTO> getComentarioUsuarioLocal(@PathVariable Long usuarioId, 
                                                                 @PathVariable Long localId) {
        List<Comentario> comentarios = comentarioService.findByUsuarioIdAndLocalId(usuarioId, localId);
        if (!comentarios.isEmpty()) {
            return ResponseEntity.ok(new ComentarioDTO(comentarios.get(0)));
        }
        return ResponseEntity.notFound().build();
    }
}