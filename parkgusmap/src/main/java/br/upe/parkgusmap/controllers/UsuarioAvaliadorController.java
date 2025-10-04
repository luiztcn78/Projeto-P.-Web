package br.upe.parkgusmap.controllers;

import br.upe.parkgusmap.entities.UsuarioAvaliador;
import br.upe.parkgusmap.services.UsuarioAvaliadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/avaliador")
public class UsuarioAvaliadorController {



    @Autowired
    private UsuarioAvaliadorService usuarioAvaliadorService;

    @PostMapping
    public ResponseEntity <UsuarioAvaliador> cadastrarUsuario(@RequestBody UsuarioAvaliador usuarioAvaliador){
        UsuarioAvaliador usuarioCadastrado = usuarioAvaliadorService.cadastraUsuarioAvaliador(usuarioAvaliador);

        if(usuarioCadastrado!=null){
            return ResponseEntity.ok(usuarioCadastrado);
        }
        return ResponseEntity.status(201).body(null);
    }

    @GetMapping("/{UsuarioAvaliadorId}")
    public ResponseEntity <UsuarioAvaliador> buscarUsuarioAvaliador(@PathVariable Long UsuarioAvaliadorId){
        UsuarioAvaliador avaliador = usuarioAvaliadorService.buscarUsuarioAvaliador(UsuarioAvaliadorId);

        if(avaliador!=null){
            return ResponseEntity.ok(avaliador);
        }

        return ResponseEntity.status(404).body(null);
    }
}
