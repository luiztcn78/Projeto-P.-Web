package br.upe.parkgusmap.services.impl;

import br.upe.parkgusmap.entities.Avaliacao;
import br.upe.parkgusmap.entities.Comentario;
import br.upe.parkgusmap.entities.Local;
import br.upe.parkgusmap.entities.UsuarioAvaliador;
import br.upe.parkgusmap.repositories.ComentarioRepository;
import br.upe.parkgusmap.repositories.LocalRepository;
import br.upe.parkgusmap.repositories.UsuarioAvaliadorRepository;
import br.upe.parkgusmap.services.ComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComentarioServiceImpl implements ComentarioService {

    @Autowired
    ComentarioRepository comentarioRepository;

    @Autowired
    UsuarioAvaliadorRepository usuarioAvaliadorRepository;

    @Autowired
    private LocalRepository localRepository;

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
        comentario.setAvaliador(avaliador);
        comentario.setLocal(local);
        comentario.setComentario(texto);

        return comentarioRepository.save(comentario);
    }

}
