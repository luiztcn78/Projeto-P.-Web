package br.upe.parkgusmap.services.impl;

import br.upe.parkgusmap.entities.Enums.Perfil;
import br.upe.parkgusmap.entities.Local;
import br.upe.parkgusmap.entities.Usuario;
import br.upe.parkgusmap.repositories.LocalRepository;
import br.upe.parkgusmap.repositories.UsuarioRepository;
import br.upe.parkgusmap.services.LocalService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocalServiceImpl implements LocalService {

    @Autowired
    private LocalRepository localRepository;
    private UsuarioRepository usuarioRepository;


    @Override
    public Local buscarLocalPorId(Long id) {
        return localRepository.findById(id).orElse(null);
    }

    @Override
    public List<Local> findAll() {
        return localRepository.findAll();
    }

    @Override
    public Optional<Local> findById(Long id) {
        return localRepository.findById(id);
    }

    @Override
    public Local save(Local local) {
        return localRepository.save(local);
    }

    @Override
    public Local update(Long id, Local local) {
        if (!localRepository.existsById(id)) {
            throw new RuntimeException("Local não encontrado com id: " + id);
        }
        local.setId(id);
        return localRepository.save(local);
    }

    @Override
    public void deleteById(Long id) {
        if (!localRepository.existsById(id)) {
            throw new RuntimeException("Local não encontrado com id: " + id);
        }
        localRepository.deleteById(id);
    }

    @Override
    public List<Local> findByAdministradorId(Long usuarioId) {
        return localRepository.findByAdministradorId(usuarioId);
    }

    @Override
    public List<Local> findByNomeContaining(String nome) {
        return localRepository.findByNomeContainingIgnoreCase(nome);
    }

    @Override
    public List<Local> findByEnderecoContaining(String endereco) {
        return localRepository.findByEnderecoContainingIgnoreCase(endereco);
    }

    @Override
    public List<Local> findByNomeOrEnderecoContaining(String termo) {
        return localRepository.findByNomeOrEnderecoContaining(termo);
    }

    @Override
    public Local addAdministradorToLocal(Long localId, Long usuarioId) {
        Local local = localRepository.findById(localId)
                .orElseThrow(() -> new RuntimeException("Local não encontrado"));

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (!usuario.getPerfil().equals(Perfil.ADMINISTRADOR)) {
            throw new RuntimeException("Usuário não possui perfil de ADMINISTRADOR");
        }

        if (!local.getAdministradores().contains(usuario)) {
            local.getAdministradores().add(usuario);
            return localRepository.save(local);
        }

        return local;
    }

    @Override
    public Local removeAdministradorFromLocal(Long localId, Long usuarioId) {
        Local local = localRepository.findById(localId)
                .orElseThrow(() -> new RuntimeException("Local não encontrado"));

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        local.getAdministradores().remove(usuario);
        return localRepository.save(local);
    }


}