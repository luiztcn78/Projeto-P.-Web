package br.upe.parkgusmap.services.impl;

import br.upe.parkgusmap.entities.Comentario;
import br.upe.parkgusmap.entities.Local;
import br.upe.parkgusmap.entities.UsuarioAvaliador;
import br.upe.parkgusmap.repositories.ComentarioRepository;
import br.upe.parkgusmap.repositories.LocalRepository;
import br.upe.parkgusmap.repositories.UsuarioAvaliadorRepository;
import br.upe.parkgusmap.services.ComentarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ComentarioServiceImpl implements ComentarioService {

    private final ComentarioRepository comentarioRepository;
    private final UsuarioAvaliadorRepository usuarioAvaliadorRepository;
    private final LocalRepository localRepository;

    @Override
    public Comentario criarComentario(Long avaliadorId, Long localId, String texto) {
        UsuarioAvaliador avaliador = usuarioAvaliadorRepository.findById(avaliadorId)
                .orElseThrow(() -> new IllegalArgumentException("Avaliador não encontrado"));

        Local local = localRepository.findById(localId)
                .orElseThrow(() -> new IllegalArgumentException("Local não encontrado"));

        if (texto == null || texto.trim().isEmpty()) {
            throw new IllegalArgumentException("Comentário vazio ou inválido");
        }

        Comentario comentario = new Comentario();
        comentario.setUsuario(avaliador);
        comentario.setLocal(local);
        comentario.setTexto(texto);

        return comentarioRepository.save(comentario);
    }

    @Override
    public List<Comentario> findAll() {
        return comentarioRepository.findAll();
    }

    @Override
    public Optional<Comentario> findById(Long id) {
        return comentarioRepository.findById(id);
    }

    @Override
    public Comentario save(Comentario comentario) {
        return comentarioRepository.save(comentario);
    }

    @Override
    public Comentario update(Long id, Comentario comentario) {
        if (!comentarioRepository.existsById(id)) {
            throw new RuntimeException("Comentário não encontrado com id: " + id);
        }
        comentario.setId(id);
        return comentarioRepository.save(comentario);
    }

    @Override
    public void deleteById(Long id) {
        if (!comentarioRepository.existsById(id)) {
            throw new RuntimeException("Comentário não encontrado com id: " + id);
        }
        comentarioRepository.deleteById(id);
    }

    @Override
    public List<Comentario> findByUsuarioId(Long usuarioId) {
        return comentarioRepository.findByUsuarioId(usuarioId);
    }

    @Override
    public List<Comentario> findByLocalId(Long localId) {
        return comentarioRepository.findByLocalId(localId);
    }
}