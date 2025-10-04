package br.upe.parkgusmap.services;

import br.upe.parkgusmap.entities.Comentario;
import java.util.List;
import java.util.Optional;

public interface ComentarioService {
    
    List<Comentario> findAll();
    
    Optional<Comentario> findById(Long id);
    
    Comentario save(Comentario comentario);
    
    Comentario update(Long id, Comentario comentario);
    
    void deleteById(Long id);
    
    Comentario criarComentario(Long avaliadorId, Long localId, String texto);
    
    List<Comentario> findByUsuarioId(Long usuarioId);
    
    List<Comentario> findByLocalId(Long localId);

    List<Comentario> findByUsuarioIdAndLocalId(Long usuarioId, Long localId);
}