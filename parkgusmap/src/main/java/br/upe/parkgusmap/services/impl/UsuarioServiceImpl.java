package br.upe.parkgusmap.services.impl;
import br.upe.parkgusmap.Exeptions.EmailJaCadastradoException;
import br.upe.parkgusmap.Exeptions.NomeDeUsuarioInvalidoException;
import br.upe.parkgusmap.Exeptions.UsuarioNaoEncontradoException;
import br.upe.parkgusmap.entities.Enums.Perfil;
import br.upe.parkgusmap.entities.Local;
import br.upe.parkgusmap.entities.Usuario;
import br.upe.parkgusmap.repositories.LocalRepository;
import br.upe.parkgusmap.repositories.UsuarioRepository;
import br.upe.parkgusmap.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    LocalRepository localRepository;
    @Autowired
    private UsuarioService usuarioService;

    @Override
    public Usuario cadastrarUsuario(Usuario usuario) {
        // validar nome
        if (usuario.getNome() == null || usuario.getNome().isEmpty()) {
            throw new NomeDeUsuarioInvalidoException(); //nome de usuario inválido
        }

        // valdar de email
        if (usuarioRepository.findByEmail(usuario.getEmail()) != null) {
            throw new EmailJaCadastradoException(usuario.getEmail());
        }

        return usuarioRepository.save(usuario);
    }

    @Override
    public boolean removerUsuario(Long id) {
        if (usuarioRepository.findById(id).isEmpty()) {
            throw new UsuarioNaoEncontradoException(id);
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
            throw new UsuarioNaoEncontradoException(id);
        }
        return usuario;
    }

    @Override
    public Usuario buscarPorEmail(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email);
        if (usuario == null) {
            throw new UsuarioNaoEncontradoException(email);
        }
        return usuario;
    }

    @Override
    public List<Local> buscarFavoritosPorId(Long idUsuario) {
        Usuario usuario = usuarioRepository.findById(idUsuario).orElse(null);

        if (usuario != null) {
            return usuario.getLocaisFavoritos();
        }
        else {
            throw new UsuarioNaoEncontradoException(idUsuario);
        }
    }

    @Override
    public boolean adicionarLocalFavorito(Long localId, Long  usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId).orElse(null);
        Local local = localRepository.findById(localId).orElse(null);

        if (usuario != null) {
            List<Local> locaisFavoritos = usuario.getLocaisFavoritos();
            locaisFavoritos.add(local);
            usuario.setLocaisFavoritos(locaisFavoritos);
            usuarioRepository.save(usuario);
            return true;
        }

        return false;
    }

    @Override
    public boolean removeLocalFavorito(Long localId,  Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId).orElse(null);
        Local local = localRepository.findById(localId).orElse(null);

        if (usuario != null) {
            List<Local> locaisFavoritos = usuario.getLocaisFavoritos();
            locaisFavoritos.remove(local);
            usuario.setLocaisFavoritos(locaisFavoritos);
            usuarioRepository.save(usuario);
            return true;
        }

        return false;
    }
}

