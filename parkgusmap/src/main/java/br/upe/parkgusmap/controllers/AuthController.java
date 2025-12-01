package br.upe.parkgusmap.controllers;

import br.upe.parkgusmap.entities.DTOs.LoginDTO;
import br.upe.parkgusmap.entities.DTOs.UsuarioCreateDTO;
import br.upe.parkgusmap.entities.DTOs.UsuarioResponsivoDTO;
import br.upe.parkgusmap.entities.Usuario;
import br.upe.parkgusmap.services.TokenService;
import br.upe.parkgusmap.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<UsuarioResponsivoDTO> cadastrarUsuario(@RequestBody UsuarioCreateDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(passwordEncoder.encode(dto.getSenha()));
        usuario.setPerfil(dto.getPerfil()); // pega o enum( o tipo)

        Usuario cadastrado = usuarioService.cadastrarUsuario(usuario);
        return ResponseEntity.status(201).body(new UsuarioResponsivoDTO(cadastrado));
    }


    @PostMapping("/login")
    public ResponseEntity<?> autenticar(@RequestBody LoginDTO loginDTO) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getSenha());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        String token = tokenService.generateToken((Usuario) auth.getPrincipal());
        return ResponseEntity.ok().body(token);
    }
}
