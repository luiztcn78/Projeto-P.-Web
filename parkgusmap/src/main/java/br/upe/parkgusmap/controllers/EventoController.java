package br.upe.parkgusmap.controllers;

import br.upe.parkgusmap.entities.DTOs.EventoDTO;
import br.upe.parkgusmap.entities.Evento;
import br.upe.parkgusmap.services.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/evento")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @GetMapping
    public List<EventoDTO> getAllEventos() {
        return eventoService.findAll().stream()
                .map(EventoDTO::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventoDTO> getEventoById(@PathVariable Long id) {
        return eventoService.findById(id)
                .map(evento -> ResponseEntity.ok(new EventoDTO(evento)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<EventoDTO> registrarEvento(@RequestBody EventoDTO eventodto){
        Evento eventoDesDTO = eventoService.eventoDTOToEvento(eventodto);
        Evento eventoResgistro = eventoService.registrarEvento(eventoDesDTO);

        if(eventoResgistro != null){
            return ResponseEntity.ok(eventodto);
        }
        return ResponseEntity.status(404).body(null);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventoDTO> updateEvento(@PathVariable Long id, @RequestBody EventoDTO eventoDTO) {
        Evento evento = eventoService.eventoDTOToEvento(eventoDTO);
        Evento updatedEvento = eventoService.update(id, evento);
        return ResponseEntity.ok(new EventoDTO(updatedEvento));
    }

    @DeleteMapping("/{eventoId}")
    public ResponseEntity<Evento> removerEvento(@PathVariable Long eventoId){
        eventoService.deleteById(eventoId);
        return ResponseEntity.status(200).body(null);
    }

    @GetMapping("/adm/{adminId}")
    public ResponseEntity<List<EventoDTO>> buscarEventosPorAdmin(@PathVariable Long adminId){
        List<Evento> eventos = eventoService.findByAdministradorId(adminId);
        List<EventoDTO> eventosDTO = eventos.stream()
                .map(EventoDTO::new)
                .collect(Collectors.toList());

        if(!eventosDTO.isEmpty()){
            return ResponseEntity.ok(eventosDTO);
        }
        return ResponseEntity.status(404).body(null);
    }

    @GetMapping("/local/{localId}")
    public ResponseEntity<List<EventoDTO>> buscarEventosLocal(@PathVariable Long localId){
        List<Evento> eventos = eventoService.findByLocalId(localId);
        List<EventoDTO> eventosDTO = eventos.stream()
                .map(EventoDTO::new)
                .collect(Collectors.toList());

        if(!eventosDTO.isEmpty()){
            return ResponseEntity.ok(eventosDTO);
        }
        return ResponseEntity.status(404).body(null);
    }

    @PutMapping("/{eventoId}/descricao")
    public ResponseEntity<EventoDTO> alterarDescricao(
            @PathVariable Long eventoId,
            @RequestParam String novaDescricao,
            @RequestParam Long usuarioId) {

        Evento evento = eventoService.alterarDescricaoEvento(eventoId, novaDescricao, usuarioId);
        EventoDTO eventoDTO = new EventoDTO(evento);
        return ResponseEntity.ok(eventoDTO);
    }

    @PutMapping("/{eventoId}/administradores/{usuarioId}")
    public ResponseEntity<EventoDTO> addAdministrador(@PathVariable Long eventoId, @PathVariable Long usuarioId) {
        Evento evento = eventoService.addAdministradorToEvento(eventoId, usuarioId);
        return ResponseEntity.ok(new EventoDTO(evento));
    }

    @DeleteMapping("/{eventoId}/administradores/{usuarioId}")
    public ResponseEntity<EventoDTO> removeAdministrador(@PathVariable Long eventoId, @PathVariable Long usuarioId) {
        Evento evento = eventoService.removeAdministradorFromEvento(eventoId, usuarioId);
        return ResponseEntity.ok(new EventoDTO(evento));
    }

    @GetMapping("/Atuais")
    public ResponseEntity<List<EventoDTO>> buscarEventosAtuais(){
        List<Evento> eventos = eventoService.findEventosFuturos();
        List<EventoDTO> eventosDTO = eventos.stream()
                .map(EventoDTO::new)
                .collect(Collectors.toList());

        if(!eventosDTO.isEmpty()){
            return ResponseEntity.ok(eventosDTO);
        }
        return ResponseEntity.status(404).body(null);
    }

    @GetMapping("/{inicio}/{fim}")
    public ResponseEntity<List<EventoDTO>> buscarEventosPeriodo(@PathVariable LocalDateTime inicio,@PathVariable LocalDateTime fim){
        List<Evento> eventos = eventoService.findByPeriodo(inicio, fim);
        List<EventoDTO> eventosDTO = eventos.stream()
                .map(EventoDTO::new)
                .collect(Collectors.toList());

        if(!eventosDTO.isEmpty()){
            return ResponseEntity.ok(eventosDTO);
        }
        return ResponseEntity.status(404).body(null);
    }
}