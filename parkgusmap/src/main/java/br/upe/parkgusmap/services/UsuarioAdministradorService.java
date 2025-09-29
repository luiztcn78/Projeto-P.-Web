package br.upe.parkgusmap.services;

import br.upe.parkgusmap.entities.UsuarioAdministrador;

import java.util.List;

public interface UsuarioAdministradorService {

    public UsuarioAdministrador cadastraUsuarioAdministrador(UsuarioAdministrador usuarioadm);
    public boolean removerUsuarioAdministrador(Long id);
    public List<UsuarioAdministrador> listarUsuariosAdministrador();
}
