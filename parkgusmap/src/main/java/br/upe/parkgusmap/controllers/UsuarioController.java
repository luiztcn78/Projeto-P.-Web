package br.upe.parkgusmap.controllers;


import br.upe.parkgusmap.entities.DTOs.UsuarioCreateDTO;
import br.upe.parkgusmap.entities.DTOs.UsuarioResponsivoDTO;
import br.upe.parkgusmap.entities.Enums.Perfil;
import br.upe.parkgusmap.entities.Usuario;
import br.upe.parkgusmap.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioResponsivoDTO> cadastrarUsuario(@RequestBody UsuarioCreateDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(dto.getSenha());
        usuario.setPerfil(dto.getPerfil()); // pega o enum( o tipo)

        Usuario cadastrado = usuarioService.cadastrarUsuario(usuario);
        return ResponseEntity.status(201).body(new UsuarioResponsivoDTO(cadastrado));
    }

    // busca o usuario pelo id
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponsivoDTO> buscarUsuarioPorId(@PathVariable Long id) {
        try {
            Usuario usuario = usuarioService.buscarUsuarioPorId(id);
            return ResponseEntity.ok(new UsuarioResponsivoDTO(usuario));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // lista geral
    @GetMapping
    public ResponseEntity<List<UsuarioResponsivoDTO>> listarUsuarios() {
        List<UsuarioResponsivoDTO> usuarios = usuarioService.listarUsuarios()
                .stream()
                .map(UsuarioResponsivoDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(usuarios);
    }

    // lista os admin
    @GetMapping("/administradores")
    public ResponseEntity<List<UsuarioResponsivoDTO>> listarAdministradores() {
        List<UsuarioResponsivoDTO> admins = usuarioService.listarPorPerfil(Perfil.ADMINISTRADOR)
                .stream()
                .map(UsuarioResponsivoDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(admins);
    }

    //Lista os avaliadors(usuarios comums)
    @GetMapping("/avaliadores")
    public ResponseEntity<List<UsuarioResponsivoDTO>> listarAvaliadores() {
        List<UsuarioResponsivoDTO> avaliadores = usuarioService.listarPorPerfil(Perfil.USUARIO)
                .stream()
                .map(UsuarioResponsivoDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(avaliadores);
    }
}
