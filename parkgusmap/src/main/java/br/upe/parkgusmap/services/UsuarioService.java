package br.upe.parkgusmap.services;

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

    boolean adicionarLocalFavorito(Long localId, Long usuarioId);

    boolean removeLocalFavorito(Long localId, Long usuarioId);
}
