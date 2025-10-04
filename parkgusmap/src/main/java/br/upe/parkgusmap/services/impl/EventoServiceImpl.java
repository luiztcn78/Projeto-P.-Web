package br.upe.parkgusmap.services.impl;

import br.upe.parkgusmap.entities.Evento;
import br.upe.parkgusmap.entities.UsuarioAvaliador;
import br.upe.parkgusmap.repositories.EventoRepository;
import br.upe.parkgusmap.repositories.UsuarioAvaliadorRepository;
import br.upe.parkgusmap.services.EventoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EventoServiceImpl implements EventoService {

    private final EventoRepository eventoRepository;
    private final UsuarioAvaliadorRepository avaliadorRepository;

    @Override
    public List<Evento> findAll() {
        return eventoRepository.findAll();
    }

    @Override
    public Optional<Evento> findById(Long id) {
        return eventoRepository.findById(id);
    }

    @Override
    public Evento registrarEvento(Evento evento) {
        return eventoRepository.save(evento);
    }

    //o que isso faz?
    @Override
    public Evento update(Long id, Evento evento) {
        if (!eventoRepository.existsById(id)) {
            throw new RuntimeException("Evento não encontrado com id: " + id);
        }
        evento.setId(id);
        return eventoRepository.save(evento);
    }

    @Override
    public void deleteById(Long id) {
        if (!eventoRepository.existsById(id)) {
            throw new RuntimeException("Evento não encontrado com id: " + id);
        }
        eventoRepository.deleteById(id);
    }

    @Override
    public List<Evento> findByLocalId(Long localId) {
        return eventoRepository.findByLocalId(localId);
    }

    @Override
    public List<Evento> findByAdministradorId(Long administradorId) {
        return eventoRepository.findByAdministradorId(administradorId);
    }

    @Override
    public List<Evento> findByPeriodo(LocalDateTime inicio, LocalDateTime fim) {
        return eventoRepository.findByDataHoraInicioBetween(inicio, fim);
    }

    @Override
    public List<Evento> findEventosFuturos() {
        return eventoRepository.findEventosFuturos(LocalDateTime.now());
    }

    @Override
    public List<Evento> findByAvaliadorId(Long avaliadorId) {
        return eventoRepository.findByAvaliadorId(avaliadorId);
    }

    @Override
    public Evento addAvaliadorToEvento(Long eventoId, Long avaliadorId) {
        Evento evento = eventoRepository.findById(eventoId)
                .orElseThrow(() -> new RuntimeException("Evento não encontrado"));
        
        UsuarioAvaliador avaliador = avaliadorRepository.findById(avaliadorId)
                .orElseThrow(() -> new RuntimeException("Avaliador não encontrado"));
        
        if (!evento.getAvaliadores().contains(avaliador)) {
            evento.getAvaliadores().add(avaliador);
            return eventoRepository.save(evento);
        }
        
        return evento;
    }

    @Override
    public Evento removeAvaliadorFromEvento(Long eventoId, Long avaliadorId) {
        Evento evento = eventoRepository.findById(eventoId)
                .orElseThrow(() -> new RuntimeException("Evento não encontrado"));
        
        UsuarioAvaliador avaliador = avaliadorRepository.findById(avaliadorId)
                .orElseThrow(() -> new RuntimeException("Avaliador não encontrado"));
        
        evento.getAvaliadores().remove(avaliador);
        return eventoRepository.save(evento);
    }
}