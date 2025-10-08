package br.upe.parkgusmap.controllers;

import br.upe.parkgusmap.entities.DTOs.EventoDTO;
import br.upe.parkgusmap.entities.Evento;
import br.upe.parkgusmap.services.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/evento")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @PostMapping
    public ResponseEntity<Evento> registrarEvento(@RequestBody EventoDTO eventodto){

        Evento eventoDesDTO = eventoService.eventoDTOToEvento(eventodto);
        Evento eventoResgistro = eventoService.registrarEvento(eventoDesDTO);

        if(eventoResgistro != null){
            return ResponseEntity.ok(eventoResgistro);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @GetMapping("/adm/{adminId}")
    public ResponseEntity<List<EventoDTO>> buscarEventosPorAdmin(@PathVariable Long adminId){
        EventoDTO dto = new EventoDTO();
        List<Evento> eventos = eventoService.findByAdministradorId(adminId);
        List<EventoDTO> eventosDTO = dto.transformarListaDTO(eventos);

        if(eventosDTO != null){
            return ResponseEntity.ok(eventosDTO);
        }
        return ResponseEntity.status(404).body(null);
    }

    @GetMapping("/local/{localId}")
    public ResponseEntity<List<EventoDTO>> buscarEventosLocal(@PathVariable Long localId){
        EventoDTO dto = new EventoDTO();
        List<Evento> eventos = eventoService.findByLocalId(localId);
        List<EventoDTO> eventosDTO = dto.transformarListaDTO(eventos);

        if(eventosDTO != null){
            return ResponseEntity.ok(eventosDTO);
        }
        return ResponseEntity.status(404).body(null);
    }

    @PutMapping("/{eventoId}/descricao")
    public ResponseEntity<Evento> alterarDescricao(
            @PathVariable Long eventoId,
            @RequestParam String novaDescricao,
            @RequestParam Long usuarioId) {

        Evento evento = eventoService.alterarDescricaoEvento(eventoId, novaDescricao, usuarioId);
        return ResponseEntity.ok(evento);
    }

    @DeleteMapping("/{eventoId}")
    public ResponseEntity<Evento> removerEvento(@PathVariable Long eventoId){
        try{
            eventoService.deleteById(eventoId);
        }
        catch(Exception e){
            return ResponseEntity.status(404).body(null);
        }
        return ResponseEntity.status(200).body(null);
    }

    @GetMapping("/Atuais")
    public ResponseEntity<List<EventoDTO>> buscarEventosAtuais(){
        EventoDTO dto = new EventoDTO();
        List<Evento> eventos = eventoService.findEventosFuturos();
        List<EventoDTO> eventosDTO = dto.transformarListaDTO(eventos);

        if(eventosDTO != null){
            return ResponseEntity.ok(eventosDTO);
        }
        return ResponseEntity.status(404).body(null);
    }

    @GetMapping("{Inicio}/{fim}")
    public ResponseEntity<List<EventoDTO>> buscarEventosPeriodo(@PathVariable LocalDateTime inicio,@PathVariable LocalDateTime fim){
        EventoDTO dto = new EventoDTO();
        List<Evento> eventos = eventoService.findByPeriodo(inicio, fim);
        List<EventoDTO> eventosDTO = dto.transformarListaDTO(eventos);

        if(eventosDTO != null){
            return ResponseEntity.ok(eventosDTO);
        }
        return ResponseEntity.status(404).body(null);
    }
}
