package br.upe.parkgusmap.services.impl;

import br.upe.parkgusmap.Exeptions.ComentarioInvalidoException;
import br.upe.parkgusmap.Exeptions.ComentarioNaoEncontradoException;
import br.upe.parkgusmap.Exeptions.LocalNaoEncontradoException;
import br.upe.parkgusmap.Exeptions.UsuarioNaoEncontradoException;
import br.upe.parkgusmap.entities.Comentario;
import br.upe.parkgusmap.entities.Local;
import br.upe.parkgusmap.entities.Usuario;
import br.upe.parkgusmap.repositories.ComentarioRepository;
import br.upe.parkgusmap.repositories.LocalRepository;
import br.upe.parkgusmap.repositories.UsuarioRepository;
import br.upe.parkgusmap.services.ComentarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ComentarioServiceImpl implements ComentarioService {

    @Autowired
    private final ComentarioRepository comentarioRepository;
    @Autowired
    private final UsuarioRepository usuarioRepository;
    @Autowired
    private final LocalRepository localRepository;

    @Override
    public Comentario criarComentario(Long usuarioId, Long localId, String texto) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new UsuarioNaoEncontradoException(usuarioId));

        Local local = localRepository.findById(localId)
                .orElseThrow(() -> new LocalNaoEncontradoException(localId));

        if (texto == null || texto.trim().isEmpty()) {
            throw new ComentarioInvalidoException();
        }

        Comentario comentario = new Comentario();
        comentario.setUsuario(usuario);
        comentario.setLocal(local);
        comentario.setTexto(texto);

        return comentarioRepository.save(comentario);
    }

    @Override
    public Comentario update(Long id, Comentario comentario) {
        if (!comentarioRepository.existsById(id)) {
            throw new ComentarioNaoEncontradoException(id);
        }
        comentario.setId(id);
        return comentarioRepository.save(comentario);
    }

    @Override
    public void deleteById(Long id) {
        if (!comentarioRepository.existsById(id)) {
            throw new ComentarioNaoEncontradoException(id);
        }
        comentarioRepository.deleteById(id);
    }

    @Override
    public List<Comentario> findByUsuarioId(Long usuarioId) {

        if(!usuarioRepository.existsById(usuarioId)) {
            throw new UsuarioNaoEncontradoException(usuarioId);
        }
        return comentarioRepository.findByUsuarioId(usuarioId);
    }

    @Override
    public List<Comentario> findByLocalId(Long localId) {
        return comentarioRepository.findByLocalId(localId);
    }

    @Override
    public List<Comentario> findByUsuarioIdAndLocalId(Long usuarioId, Long localId) {
        if(!usuarioRepository.existsById(usuarioId)) {
            throw new UsuarioNaoEncontradoException(usuarioId);
        }
        if(!localRepository.existsById(localId)) {
            throw new LocalNaoEncontradoException(localId);
        }
        return comentarioRepository.findByUsuarioIdAndLocalId(usuarioId, localId);
    }

    @Override
    public List<Comentario> findAll() {
        return comentarioRepository.findAll();
    }

    @Override
    public Comentario findById(Long id) {
        return comentarioRepository.findById(id)
                .orElseThrow(() -> new ComentarioNaoEncontradoException(id));
    }
}