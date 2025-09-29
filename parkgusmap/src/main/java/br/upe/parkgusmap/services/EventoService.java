package br.upe.parkgusmap.services;

import br.upe.parkgusmap.entities.Evento;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface EventoService {
    
    List<Evento> findAll();
    
    Optional<Evento> findById(Long id);
    
    Evento save(Evento evento);
    
    Evento update(Long id, Evento evento);
    
    void deleteById(Long id);
    
    List<Evento> findByLocalId(Long localId);
    
    List<Evento> findByAdministradorId(Long administradorId);
    
    List<Evento> findByPeriodo(LocalDateTime inicio, LocalDateTime fim);
    
    List<Evento> findEventosFuturos();
    
    List<Evento> findByAvaliadorId(Long avaliadorId);
    
    Evento addAvaliadorToEvento(Long eventoId, Long avaliadorId);
    
    Evento removeAvaliadorFromEvento(Long eventoId, Long avaliadorId);
}