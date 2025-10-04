package br.upe.parkgusmap.services.impl;
import br.upe.parkgusmap.entities.Enums.Perfil;
import br.upe.parkgusmap.entities.Usuario;
import br.upe.parkgusmap.repositories.UsuarioRepository;
import br.upe.parkgusmap.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public Usuario cadastrarUsuario(Usuario usuario) {
        // validar nome
        if (usuario.getNome() == null || usuario.getNome().isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser vazio");
        }

        // valdar de email
        if (usuarioRepository.findByEmail(usuario.getEmail()) != null) {
            throw new IllegalArgumentException("Email já cadastrado");
        }

        return usuarioRepository.save(usuario);
    }

    @Override
    public boolean removerUsuario(Long id) {
        if (usuarioRepository.findById(id).isEmpty()) {
            throw new IllegalArgumentException("Usuário não encontrado");
        }

        usuarioRepository.deleteById(id);
        return usuarioRepository.findById(id).isEmpty();
    }

    @Override
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public List<Usuario> listarPorPerfil(Perfil perfil) {
        return usuarioRepository.findByPerfil(perfil);
    }

    @Override
    public Usuario buscarUsuarioPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if (usuario == null) {
            throw new IllegalArgumentException("Usuário não encontrado");
        }
        return usuario;
    }

    @Override
    public Usuario buscarPorEmail(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email);
        if (usuario == null) {
            throw new IllegalArgumentException("Usuário não encontrado");
        }
        return usuario;
    }
}

