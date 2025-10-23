package br.upe.parkgusmap.controllers;


import br.upe.parkgusmap.entities.Comentario;
import br.upe.parkgusmap.entities.DTOs.UsuarioCreateDTO;
import br.upe.parkgusmap.entities.DTOs.UsuarioResponsivoDTO;
import br.upe.parkgusmap.entities.Denuncia;
import br.upe.parkgusmap.entities.Enums.Perfil;
import br.upe.parkgusmap.entities.Local;
import br.upe.parkgusmap.entities.Usuario;
import br.upe.parkgusmap.services.DenunciaService;
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

    @Autowired
    DenunciaService denunciaService;


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

    @DeleteMapping("/{usuarioId}")
    public ResponseEntity<Usuario> removerUsuario(@PathVariable Long usuarioId){
        boolean removido = usuarioService.removerUsuario(usuarioId);

        if(removido){
            return ResponseEntity.status(200).body(null);
        }

        return ResponseEntity.status(404).body(null);
    }

    //Listar Favoritos
    @GetMapping("/fav/{usuarioId}")
    public ResponseEntity<List<Local>> buscarFavoritosUsuario(@PathVariable Long usuarioId){
        List<Local> locaisFavoritos = usuarioService.buscarFavoritosPorId(usuarioId);

        if(locaisFavoritos.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(locaisFavoritos);
    }
    //add fav
    @PutMapping("/fav/{localId}/{usuarioId}")
    public ResponseEntity<UsuarioResponsivoDTO> adicionarLocalAosFavoritos(@PathVariable Long localId, @PathVariable Long usuarioId){
        boolean sucesso = usuarioService.adicionarLocalFavorito(localId, usuarioId);
        Usuario usuario = usuarioService.buscarUsuarioPorId(usuarioId);
        UsuarioResponsivoDTO dto = new UsuarioResponsivoDTO(usuario);
        if(sucesso){
            return ResponseEntity.status(200).body(dto);
        }
        return ResponseEntity.badRequest().body(dto);
    }
    //remove fav
    @PutMapping("/fav/remove/{localId}/{usuarioId}")
    public ResponseEntity<UsuarioResponsivoDTO> removerLocalFavoritos(@PathVariable Long localId, @PathVariable Long usuarioId){
        boolean sucesso = usuarioService.removeLocalFavorito(localId, usuarioId);
        Usuario usuario = usuarioService.buscarUsuarioPorId(usuarioId);
        UsuarioResponsivoDTO dto = new UsuarioResponsivoDTO(usuario);
        if(sucesso){
            return ResponseEntity.status(200).body(dto);
        }
        return ResponseEntity.badRequest().body(dto);
    }

    @PostMapping("/denun")
    public ResponseEntity<Denuncia> fazerDenuncia(@RequestBody Denuncia denuncia, @RequestParam Long idDenunciado){
        denuncia.setIdDenunciado(idDenunciado);
        denunciaService.fazerDenuncia(denuncia);
        return ResponseEntity.status(200).body(denuncia);
    }

    @GetMapping("/administradores/denuncias/{idUsuario}")
    public ResponseEntity<List<Denuncia>> listarDenuncias(@PathVariable Long idUsuario){
        Usuario usuario = usuarioService.buscarUsuarioPorId(idUsuario);

        List<Denuncia> denuncias = null;

        if(usuario.getPerfil() == Perfil.ADMINISTRADOR){
            denuncias = denunciaService.listarTodasDenuncias();
            return ResponseEntity.status(200).body(denuncias);
        }
        //retornar erro
        return ResponseEntity.status(404).body(denuncias);
    }

    @GetMapping("/administradores/denuncia/{idUsuario}/{idDenun}")
    public ResponseEntity<Comentario> buscarComentarioDenunciado(@PathVariable Long idUsuario, @PathVariable Long idDenun){
        Usuario usuario = usuarioService.buscarUsuarioPorId(idUsuario);

        Comentario comentario = null;
        if(usuario.getPerfil() == Perfil.ADMINISTRADOR){
            comentario = denunciaService.encontrarComentarioDenunciado(idDenun);
            return ResponseEntity.status(200).body(comentario);
        }
        return ResponseEntity.status(404).body(comentario);
    }
}

