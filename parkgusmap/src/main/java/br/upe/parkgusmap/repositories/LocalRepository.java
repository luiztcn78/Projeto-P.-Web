package br.upe.parkgusmap.repositories;

import br.upe.parkgusmap.entities.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocalRepository extends JpaRepository<Local, Long> {
    
    List<Local> findByAdministradorId(Long administradorId);
    
    List<Local> findByNomeContainingIgnoreCase(String nome);
    
    List<Local> findByEnderecoContainingIgnoreCase(String endereco);
    
    @Query("SELECT l FROM Local l WHERE LOWER(l.nome) LIKE LOWER(CONCAT('%', :termo, '%')) OR LOWER(l.endereco) LIKE LOWER(CONCAT('%', :termo, '%'))")
    List<Local> findByNomeOrEnderecoContaining(@Param("termo") String termo);

    @Query("SELECT l FROM Local l JOIN l.administradores a WHERE a.id = :usuarioId")
    List<Local> findByAdministrador(@Param("usuarioId") Long usuarioId);
}