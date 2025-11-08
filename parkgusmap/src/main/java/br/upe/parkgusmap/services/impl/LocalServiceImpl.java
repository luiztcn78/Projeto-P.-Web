package br.upe.parkgusmap.services.impl;

import br.upe.parkgusmap.Exeptions.AcessoNaoPermitidoException;
import br.upe.parkgusmap.Exeptions.LocalNaoEncontradoException;
import br.upe.parkgusmap.Exeptions.UsuarioNaoEncontradoException;
import br.upe.parkgusmap.entities.Enums.Perfil;
import br.upe.parkgusmap.entities.Local;
import br.upe.parkgusmap.entities.Usuario;
import br.upe.parkgusmap.repositories.LocalRepository;
import br.upe.parkgusmap.repositories.UsuarioRepository;
import br.upe.parkgusmap.services.LocalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocalServiceImpl implements LocalService {

    @Autowired
    private LocalRepository localRepository;
    @Autowired
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
            throw new LocalNaoEncontradoException(id);
        }
        local.setId(id);
        return localRepository.save(local);
    }

    @Override
    public void deleteById(Long id) {
        if (!localRepository.existsById(id)) {
            throw new LocalNaoEncontradoException(id);
        }
        localRepository.deleteById(id);
    }

    @Override
    public List<Local> findByAdministradorId(Long usuarioId) {
        if(!usuarioRepository.existsById(usuarioId)){
            throw new UsuarioNaoEncontradoException(usuarioId);
        }
        return localRepository.findByAdministradoresId(usuarioId);
    }

    @Override
    public List<Local> findByNomeContaining(String nome) {
        return localRepository.findByNomeContainingIgnoreCase(nome); //precisa de erro?
    }

    @Override
    public List<Local> findByEnderecoContaining(String endereco) {
        return localRepository.findByEnderecoContainingIgnoreCase(endereco); //precisa de erro?
    }

    @Override
    public List<Local> findByNomeOrEnderecoContaining(String termo) {
        return localRepository.findByNomeOrEnderecoContaining(termo);
    }

    @Override
    public Local addAdministradorToLocal(Long localId, Long usuarioId) {
        Local local = localRepository.findById(localId)
                .orElseThrow(() -> new LocalNaoEncontradoException(localId));

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new UsuarioNaoEncontradoException(usuarioId));

        if (!usuario.getPerfil().equals(Perfil.ADMINISTRADOR)) {
            throw new AcessoNaoPermitidoException();
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
                .orElseThrow(() -> new LocalNaoEncontradoException(localId));

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new UsuarioNaoEncontradoException(usuarioId));

        local.getAdministradores().remove(usuario);
        return localRepository.save(local);
    }

    @Override
    public Local alterarDescricaoLocal(Long localId, String novaDescricao, Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new UsuarioNaoEncontradoException(usuarioId));

        if (usuario.getPerfil() != Perfil.ADMINISTRADOR) {
            throw new AcessoNaoPermitidoException();
        }

        Local local = localRepository.findById(localId)
                .orElseThrow(() -> new LocalNaoEncontradoException(localId));

        // aalterando a descrição
        local.setDescricao(novaDescricao);

        return localRepository.save(local);

    }


}