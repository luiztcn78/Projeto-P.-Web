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
        if(validarUsuarioAvaliador(usuarioava)){
            return usuarioAvaliadorRepository.save(usuarioava);
        }
        return null;
    }

    @Override
    public boolean validarUsuarioAvaliador(UsuarioAvaliador usuarioava) {
        if(usuarioAvaliadorRepository.findByEmail(usuarioava.getEmail()) != null){

            throw new IllegalArgumentException("Email já cadastrado");

        }
        else if (usuarioava.getNome() == null || usuarioava.getNome().isEmpty()) {

            throw new IllegalArgumentException("Nome não pode ser vazio");

        }
        return true;
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

    @Override
    public UsuarioAvaliador buscarUsuarioAvaliador(Long id) {
        return usuarioAvaliadorRepository.findById(id).orElse(null);
    }
}