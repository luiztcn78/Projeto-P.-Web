package br.upe.parkgusmap.repositories;

import br.upe.parkgusmap.entities.Denuncia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DenunciaRepository extends JpaRepository<Denuncia, Long> {
}
