package br.upe.parkgusmap.services;

import br.upe.parkgusmap.entities.DTOs.UsuarioResponsivoDTO;
import br.upe.parkgusmap.entities.Enums.Perfil;
import br.upe.parkgusmap.entities.Local;
import br.upe.parkgusmap.entities.Usuario;

import java.util.List;

public interface UsuarioService {
    Usuario cadastrarUsuario(Usuario usuario);

    boolean removerUsuario(Long id);

    List<Usuario> listarUsuarios();

    List<Usuario> listarPorPerfil(Perfil perfil);

    Usuario buscarUsuarioPorId(Long id);

    Usuario buscarPorEmail(String email);

    List<Local> buscarFavoritosPorId(Long id);

    UsuarioResponsivoDTO adicionarLocalFavorito(Long localId, Long usuarioId);

    void removeLocalFavorito(Long localId, Long usuarioId);
}
