package br.upe.parkgusmap.services;


import br.upe.parkgusmap.entities.UsuarioAdministrador;
import br.upe.parkgusmap.entities.UsuarioAvaliador;

import java.util.List;

public interface UsuarioAvaliadorService {
    public UsuarioAvaliador cadastraUsuarioAvaliador(UsuarioAvaliador usuarioava);
    public boolean removerUsuarioAvaliador(Long id);
    public List<UsuarioAvaliador> listarUsuariosAvaliador();
    UsuarioAvaliador buscarUsuarioAvaliador(Long id);
}
