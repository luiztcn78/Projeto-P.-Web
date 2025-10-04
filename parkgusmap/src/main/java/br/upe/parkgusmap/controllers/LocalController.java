package br.upe.parkgusmap.controllers;

import br.upe.parkgusmap.entities.Local;
import br.upe.parkgusmap.services.LocalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/locais")
@RequiredArgsConstructor
public class LocalController {

    private final LocalService localService;

    @GetMapping
    public List<Local> getAllLocais() {
        return localService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Local> getLocalById(@PathVariable Long id) {
        Optional<Local> local = localService.findById(id);
        return local.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Local createLocal(@RequestBody Local local) {
        return localService.save(local);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Local> updateLocal(@PathVariable Long id, @RequestBody Local local) {
        try {
            Local updatedLocal = localService.update(id, local);
            return ResponseEntity.ok(updatedLocal);
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
    public List<Local> getLocaisByAdministrador(@PathVariable Long administradorId) {
        return localService.findByAdministradorId(administradorId);
    }

    @GetMapping("/buscar")
    public List<Local> searchLocais(@RequestParam String termo) {
        return localService.findByNomeOrEnderecoContaining(termo);
    }

    @GetMapping("/buscar/nome")
    public List<Local> searchLocaisPorNome(@RequestParam String nome) {
        return localService.findByNomeContaining(nome);
    }

    @GetMapping("/buscar/endereco")
    public List<Local> searchLocaisPorEndereco(@RequestParam String endereco) {
        return localService.findByEnderecoContaining(endereco);
    }
}