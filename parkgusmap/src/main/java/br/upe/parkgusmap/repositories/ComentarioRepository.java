package br.upe.parkgusmap.repositories;

import br.upe.parkgusmap.entities.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
    List<Comentario> findByUsuarioId(Long usuarioId);
    List<Comentario> findByLocalId(Long localId);
    List<Comentario> findByUsuarioIdAndLocalId(Long usuarioId, Long localId);
}