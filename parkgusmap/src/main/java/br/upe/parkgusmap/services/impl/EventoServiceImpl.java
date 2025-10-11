package br.upe.parkgusmap.services.impl;

import br.upe.parkgusmap.entities.DTOs.EventoDTO;
import br.upe.parkgusmap.entities.Enums.Perfil;
import br.upe.parkgusmap.entities.Evento;
import br.upe.parkgusmap.entities.Usuario;
import br.upe.parkgusmap.repositories.EventoRepository;
import br.upe.parkgusmap.repositories.UsuarioRepository;
import br.upe.parkgusmap.services.EventoService;
import br.upe.parkgusmap.services.LocalService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EventoServiceImpl implements EventoService {

    @Autowired
    private final EventoRepository eventoRepository;
    @Autowired
    private final UsuarioRepository usuarioRepository;
    @Autowired
    private final LocalService localService;

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
    public List<Evento> findByPeriodo(LocalDateTime inicio, LocalDateTime fim) {
        return eventoRepository.findByDataHoraInicioBetween(inicio, fim);
    }

    @Override
    public List<Evento> findEventosFuturos() {
        return eventoRepository.findEventosFuturos(LocalDateTime.now());
    }

    @Override
    public List<Evento> findByAdministradorId(Long usuarioId) {
        return eventoRepository.findByAdministradorId(usuarioId);
    }

    @Override
    public Evento addAdministradorToEvento(Long eventoId, Long usuarioId) {
        Evento evento = eventoRepository.findById(eventoId)
                .orElseThrow(() -> new RuntimeException("Evento não encontrado"));

        Usuario admin = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (admin.getPerfil() != Perfil.ADMINISTRADOR) {
            throw new RuntimeException("O usuário não é um administrador");
        }

        if (!evento.getAdministradores().contains(admin)) {
            evento.getAdministradores().add(admin);
            return eventoRepository.save(evento);
        }
        return evento;
    }

    @Override
    public Evento removeAdministradorFromEvento(Long eventoId, Long usuarioId) {
        Evento evento = eventoRepository.findById(eventoId)
                .orElseThrow(() -> new RuntimeException("Evento não encontrado"));

        Usuario admin = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        evento.getAdministradores().remove(admin);
        return eventoRepository.save(evento);
    }

    public Evento eventoDTOToEvento(EventoDTO eventoDTO) {
        Evento evento = new Evento();
        evento.setId(eventoDTO.getId());
        evento.setDataHoraFim(eventoDTO.getDataFim());
        evento.setEndereco(eventoDTO.getEndereco());
        evento.setNome(eventoDTO.getNome());
        evento.setDataHoraInicio(eventoDTO.getDataInicio());
        evento.setLocal(localService.buscarLocalPorId(eventoDTO.getLocalId()));
        evento.setImagens(eventoDTO.getImagens());
        evento.setDescricao(eventoDTO.getDescricao());

        // Se você quiser adicionar administradores diretamente do DTO:
        if (eventoDTO.getAdministradoresIds() != null && !eventoDTO.getAdministradoresIds().isEmpty()) {
            List<Usuario> admins = usuarioRepository.findAllById(eventoDTO.getAdministradoresIds());
            evento.setAdministradores(admins);
        }

        return evento;
    }

    @Override
    public Evento alterarDescricaoEvento(Long eventoId, String novaDescricao, Long usuarioId) {

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (usuario.getPerfil() != Perfil.ADMINISTRADOR) {
            throw new RuntimeException("Apenas administradores podem alterar a descrição do evento");
        }

        Evento evento = eventoRepository.findById(eventoId)
                .orElseThrow(() -> new RuntimeException("Evento não encontrado"));

        // aalterando a descrição
        evento.setDescricao(novaDescricao);

        return eventoRepository.save(evento);

        //acho que não faz sentido

    }


}