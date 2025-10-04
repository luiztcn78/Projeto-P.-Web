package br.upe.parkgusmap.services;

import br.upe.parkgusmap.entities.Avaliacao;
import java.util.List;
import java.util.Optional;

public interface AvaliacaoService {
    
    List<Avaliacao> findAll();
    
    Optional<Avaliacao> findById(Long id);
    
    Avaliacao save(Avaliacao avaliacao);
    
    Avaliacao update(Long id, Avaliacao avaliacao);
    
    void deleteById(Long id);
    
    Avaliacao criarAvaliacao(Long avaliadorId, Long localId, int nota);
    
    List<Avaliacao> findByUsuarioId(Long usuarioId);
    
    List<Avaliacao> findByLocalId(Long localId);

    List<Avaliacao> findByUsuarioIdAndLocalId(Long usuarioId, Long localId);
}