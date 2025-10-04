package br.upe.parkgusmap.controllers;

import br.upe.parkgusmap.entities.DTOs.LocalDTO;
import br.upe.parkgusmap.entities.Local;
import br.upe.parkgusmap.services.LocalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/locais")
@RequiredArgsConstructor
public class LocalController {

    private final LocalService localService;

    @GetMapping
    public List<LocalDTO> getAllLocais() {
        return localService.findAll().stream()
                .map(LocalDTO::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LocalDTO> getLocalById(@PathVariable Long id) {
        Optional<Local> local = localService.findById(id);
        return local.map(l -> ResponseEntity.ok(new LocalDTO(l)))
                   .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public LocalDTO createLocal(@Valid @RequestBody LocalDTO localDTO) {
        Local local = convertToEntity(localDTO);
        Local savedLocal = localService.save(local);
        return new LocalDTO(savedLocal);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LocalDTO> updateLocal(@PathVariable Long id, @Valid @RequestBody LocalDTO localDTO) {
        try {
            Local local = convertToEntity(localDTO);
            Local updatedLocal = localService.update(id, local);
            return ResponseEntity.ok(new LocalDTO(updatedLocal));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLocal(@PathVariable Long id) {
        try {
            localService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/administrador/{administradorId}")
    public List<LocalDTO> getLocaisByAdministrador(@PathVariable Long administradorId) {
        return localService.findByAdministradorId(administradorId).stream()
                .map(LocalDTO::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/buscar")
    public List<LocalDTO> searchLocais(@RequestParam String termo) {
        return localService.findByNomeOrEnderecoContaining(termo).stream()
                .map(LocalDTO::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/buscar/nome")
    public List<LocalDTO> searchLocaisPorNome(@RequestParam String nome) {
        return localService.findByNomeContaining(nome).stream()
                .map(LocalDTO::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/buscar/endereco")
    public List<LocalDTO> searchLocaisPorEndereco(@RequestParam String endereco) {
        return localService.findByEnderecoContaining(endereco).stream()
                .map(LocalDTO::new)
                .collect(Collectors.toList());
    }

    private Local convertToEntity(LocalDTO localDTO) {
        Local local = new Local();
        local.setId(localDTO.getId());
        local.setNome(localDTO.getNome());
        local.setEndereco(localDTO.getEndereco());
        local.setDescricao(localDTO.getDescricao());
        local.setInformacoes(localDTO.getInformacoes());
        local.setImagens(localDTO.getImagens());
        local.setEspecificacoes(localDTO.getEspecificacoes());
        return local;
    }
}