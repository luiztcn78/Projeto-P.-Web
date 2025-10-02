package br.upe.parkgusmap.repositories;

import br.upe.parkgusmap.entities.Avaliacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {
    List<Avaliacao> findByUsuarioId(Long usuarioId);
    List<Avaliacao> findByLocalId(Long localId);
    List<Avaliacao> findByUsuarioIdAndLocalId(Long usuarioId, Long localId);
}