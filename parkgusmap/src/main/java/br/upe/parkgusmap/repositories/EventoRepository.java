package br.upe.parkgusmap.repositories;

import br.upe.parkgusmap.entities.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {
    
    List<Evento> findByLocalId(Long localId);
    
    List<Evento> findByAdministradorId(Long administradorId);
    
    List<Evento> findByDataHoraInicioBetween(LocalDateTime inicio, LocalDateTime fim);
    
    @Query("SELECT e FROM Evento e WHERE e.dataHoraInicio > :agora ORDER BY e.dataHoraInicio ASC")
    List<Evento> findEventosFuturos(@Param("agora") LocalDateTime agora);
    
    @Query("SELECT e FROM Evento e JOIN e.avaliadores a WHERE a.id = :avaliadorId")
    List<Evento> findByAvaliadorId(@Param("avaliadorId") Long avaliadorId);
}