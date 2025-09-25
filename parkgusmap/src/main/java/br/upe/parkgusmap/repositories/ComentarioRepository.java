package br.upe.parkgusmap.repositories;

import br.upe.parkgusmap.entities.Avaliacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComentarioRepository extends JpaRepository<Avaliacao, Long> {
}
