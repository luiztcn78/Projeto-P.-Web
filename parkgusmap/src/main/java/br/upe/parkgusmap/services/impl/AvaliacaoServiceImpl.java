package br.upe.parkgusmap.services.impl;

import br.upe.parkgusmap.Exeptions.AvaliacaoNaoEncontradaException;
import br.upe.parkgusmap.Exeptions.LocalNaoEncontradoException;
import br.upe.parkgusmap.Exeptions.NotaInvalidaException;
import br.upe.parkgusmap.Exeptions.UsuarioNaoEncontradoException;
import br.upe.parkgusmap.entities.Avaliacao;
import br.upe.parkgusmap.entities.Local;
import br.upe.parkgusmap.entities.Usuario;
import br.upe.parkgusmap.repositories.AvaliacaoRepository;
import br.upe.parkgusmap.repositories.LocalRepository;
import br.upe.parkgusmap.repositories.UsuarioRepository;
import br.upe.parkgusmap.services.AvaliacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AvaliacaoServiceImpl implements AvaliacaoService {

    @Autowired
    private final AvaliacaoRepository avaliacaoRepository;
    @Autowired
    private final UsuarioRepository usuarioRepository;
    @Autowired
    private final LocalRepository localRepository;

    @Override
    public Avaliacao criarAvaliacao(Long usuarioId, Long localId, int nota) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new UsuarioNaoEncontradoException(usuarioId));

        Local local = localRepository.findById(localId)
                .orElseThrow(() -> new LocalNaoEncontradoException(localId));

        if (nota < 1 || nota > 5) {
            throw new NotaInvalidaException(); //Nota inválida
        }

        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setUsuario(usuario);
        avaliacao.setLocal(local);
        avaliacao.setNota(nota);

        return avaliacaoRepository.save(avaliacao);
    }

    @Override
    public List<Avaliacao> findAll() {
        return avaliacaoRepository.findAll();
    }

    @Override
    public Optional<Avaliacao> findById(Long id) {
        return avaliacaoRepository.findById(id);
    }

    @Override
    public Avaliacao save(Avaliacao avaliacao) {
        return avaliacaoRepository.save(avaliacao);
    }

    @Override
    public Avaliacao update(Long id, Avaliacao avaliacao) {
        if (!avaliacaoRepository.existsById(id)) {
            throw new AvaliacaoNaoEncontradaException(id);
        }
        avaliacao.setId(id);
        return avaliacaoRepository.save(avaliacao);
    }

    @Override
    public void deleteById(Long id) {
        if (!avaliacaoRepository.existsById(id)) {
            throw new AvaliacaoNaoEncontradaException(id);
        }
        avaliacaoRepository.deleteById(id);
    }

    @Override
    public List<Avaliacao> findByUsuarioId(Long usuarioId) {
        return avaliacaoRepository.findByUsuarioId(usuarioId);
    }

    @Override
    public List<Avaliacao> findByLocalId(Long localId) {
        return avaliacaoRepository.findByLocalId(localId);
    }

    @Override
    public List<Avaliacao> findByUsuarioIdAndLocalId(Long usuarioId, Long localId) {
        if(!usuarioRepository.existsById(usuarioId)){
            throw new UsuarioNaoEncontradoException(usuarioId);
        }
        if(!localRepository.existsById(localId)){
            throw new LocalNaoEncontradoException(localId);
        }
        return avaliacaoRepository.findByUsuarioIdAndLocalId(usuarioId, localId);
    }


}