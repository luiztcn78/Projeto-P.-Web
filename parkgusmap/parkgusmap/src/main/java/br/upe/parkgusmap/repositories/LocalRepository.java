package br.upe.parkgusmap.repositories;

import br.upe.parkgusmap.entities.Avaliacao;
import br.upe.parkgusmap.entities.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocalRepository extends JpaRepository<Local, Long> {

}
