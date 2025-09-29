package br.upe.parkgusmap.services.impl;

import br.upe.parkgusmap.entities.UsuarioAvaliador;
import br.upe.parkgusmap.repositories.UsuarioAdministradorRepository;
import br.upe.parkgusmap.repositories.UsuarioAvaliadorRepository;
import br.upe.parkgusmap.services.UsuarioAdministradorService;
import br.upe.parkgusmap.services.UsuarioAvaliadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioAvaliadorImpl implements UsuarioAvaliadorService {

    @Autowired
    UsuarioAvaliadorRepository usuarioAvaliadorRepository;

    @Override
    public UsuarioAvaliador cadastraUsuarioAvaliador(UsuarioAvaliador usuarioava) {
        validarNome(usuarioava.getNome());
        validarEmail(usuarioava.getEmail());

        return usuarioAvaliadorRepository.save(usuarioava);
    }

    public void validarEmail(String email){
        if(usuarioAvaliadorRepository.findByEmail(email) != null){
            throw new IllegalArgumentException("Email já cadastrado");
        }
    }

    private void validarNome(String nome) {
        if (nome == null || nome.isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser vazio");
        }
    }

    @Override
    public boolean removerUsuarioAvaliador(Long id) {
        usuarioAvaliadorRepository.deleteById(id);

        return usuarioAvaliadorRepository.findById(id).isEmpty();
    }

    @Override
    public List<UsuarioAvaliador> listarUsuariosAvaliador() {
        return usuarioAvaliadorRepository.findAll();
    }
}