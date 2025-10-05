package br.upe.parkgusmap.services;

import br.upe.parkgusmap.entities.DTOs.EventoDTO;
import br.upe.parkgusmap.entities.Evento;
import br.upe.parkgusmap.entities.Usuario;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface EventoService {
    
    List<Evento> findAll();
    
    Optional<Evento> findById(Long id);
    
    Evento registrarEvento(Evento evento);
    
    Evento update(Long id, Evento evento);
    
    void deleteById(Long id);
    
    List<Evento> findByLocalId(Long localId);
    
    List<Evento> findByAdministradorId(Long usuarioId);
    
    List<Evento> findByPeriodo(LocalDateTime inicio, LocalDateTime fim);
    
    List<Evento> findEventosFuturos();
    
    Evento addAdministradorToEvento(Long eventoId, Long usuarioId);
    
    Evento removeAdministradorFromEvento(Long eventoId, Long usuarioId);

    Evento eventoDTOToEvento(EventoDTO eventoDTO);

    Evento alterarDescricaoEvento(Long eventoId, String novaDescricao, Long usuarioId);

}