package br.upe.parkgusmap.services.impl;

import br.upe.parkgusmap.entities.UsuarioAdministrador;
import br.upe.parkgusmap.repositories.UsuarioAdministradorRepository;
import br.upe.parkgusmap.services.AvaliacaoService;
import br.upe.parkgusmap.services.UsuarioAdministradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioAdministradorImpl implements UsuarioAdministradorService {

    @Autowired
    UsuarioAdministradorRepository  usuarioAdministradorRepository;

    @Override
    public UsuarioAdministrador cadastraUsuarioAdministrador(UsuarioAdministrador usuarioadm) {
        validarNome(usuarioadm.getNome());
        validarEmail(usuarioadm.getEmail());

        return usuarioAdministradorRepository.save(usuarioadm);
    }

    public void validarEmail(String email){
        if(usuarioAdministradorRepository.findByEmail(email) != null){
            throw new IllegalArgumentException("Email já cadastrado");
        }
    }

    private void validarNome(String nome) {
        if (nome == null || nome.isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser vazio");
        }
    }

    @Override
    public boolean removerUsuarioAdministrador(Long id) {
        usuarioAdministradorRepository.deleteById(id);

        return usuarioAdministradorRepository.findById(id).isEmpty();
    }

    @Override
    public List<UsuarioAdministrador> listarUsuariosAdministrador() {
        return usuarioAdministradorRepository.findAll();
    }

    @Override
    public UsuarioAdministrador buscarUsuarioAdministradorPorId(Long id) {
        return usuarioAdministradorRepository.findById(id).orElse(null);

    }

}

