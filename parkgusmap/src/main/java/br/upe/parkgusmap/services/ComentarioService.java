package br.upe.parkgusmap.services;

import br.upe.parkgusmap.entities.Comentario;
import java.util.List;

public interface ComentarioService {

    Comentario update(Long id, Comentario comentario);
    
    void deleteById(Long id);
    
    Comentario criarComentario(Long avaliadorId, Long localId, String texto);
    
    List<Comentario> findByUsuarioId(Long usuarioId);
    
    List<Comentario> findByLocalId(Long localId);

    List<Comentario> findByUsuarioIdAndLocalId(Long usuarioId, Long localId);

    List<Comentario> findAll();

    Comentario findById(Long id);
}