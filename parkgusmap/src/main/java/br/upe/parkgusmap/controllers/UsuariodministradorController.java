package br.upe.parkgusmap.controllers;

import br.upe.parkgusmap.entities.Local;
import br.upe.parkgusmap.entities.UsuarioAdministrador;
import br.upe.parkgusmap.services.UsuarioAdministradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuariosadministradores")

public class UsuariodministradorController {

    @Autowired
    private UsuarioAdministradorService usuarioAdministradorService;

    @GetMapping
    public ResponseEntity<List<UsuarioAdministrador>> listarUsuariosAdministrador() {
        List<UsuarioAdministrador> administradores = usuarioAdministradorService.listarUsuariosAdministrador();
        return ResponseEntity.ok(administradores);
    }

    @GetMapping("/{id}/local")
    public ResponseEntity<List<Local>> buscarLocaisAdministrados(@PathVariable Long id){

        UsuarioAdministrador administrador = usuarioAdministradorService.buscarUsuarioAdministradorPorId(id);

        if (administrador == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(administrador.getLocaisAdministrados());
    }

    @PostMapping
    public ResponseEntity<UsuarioAdministrador> cadastrarUsuario(@RequestBody UsuarioAdministrador administrador){

        UsuarioAdministrador usuarioAdministrador = usuarioAdministradorService.
                cadastraUsuarioAdministrador(administrador);

        if(usuarioAdministrador == null){
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.status(201).body(usuarioAdministrador);
    }

    /* q?????
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioAdministradorDTO> buscarUsuarioAdministradorPorID(@PathVariable Long id){
        UsuarioAdministrador administrador = usuarioAdministradorService.buscarUsuarioAdministradorPorId(id);
        if(administrador == null){
            return ResponseEntity.notFound().build();
            //return ResponseEntity.status(410).body(null);
        }
        //return ResponseEntity.ok(usuario);
        UsuarioAdministrador usuarioAdmDTO = new UsuarioAdministrador(DTOadministrador);
        return ResponseEntity.status(200).body(usuarioAdmDTO);
    }*/
}
