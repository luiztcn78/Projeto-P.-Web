package br.upe.parkgusmap.repositories;

import br.upe.parkgusmap.entities.Enums.Perfil;
import br.upe.parkgusmap.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByEmail(String email);

    Usuario findByNome(String nome);

    List<Usuario> findByPerfil(Perfil perfil);
}
