package br.upe.parkgusmap.repositories;



import br.upe.parkgusmap.entities.UsuarioAvaliador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioAvaliadorRepository extends JpaRepository<UsuarioAvaliador, Long> {
    public UsuarioAvaliador findByEmail(String email);
    public UsuarioAvaliador findByNome(String nome);
}

