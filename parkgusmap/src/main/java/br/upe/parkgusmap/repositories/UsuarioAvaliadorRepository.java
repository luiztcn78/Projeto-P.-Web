package br.upe.parkgusmap.repositories;


import br.upe.parkgusmap.entities.UsuarioAdministrador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioAvaliadorRepository extends JpaRepository<UsuarioAdministrador, Long> {
}

