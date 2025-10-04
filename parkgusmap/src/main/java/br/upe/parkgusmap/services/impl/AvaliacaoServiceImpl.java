package br.upe.parkgusmap.services.impl;

import br.upe.parkgusmap.entities.Avaliacao;
import br.upe.parkgusmap.entities.Local;
import br.upe.parkgusmap.entities.UsuarioAvaliador;
import br.upe.parkgusmap.repositories.AvaliacaoRepository;
import br.upe.parkgusmap.repositories.LocalRepository;
import br.upe.parkgusmap.repositories.UsuarioAvaliadorRepository;
import br.upe.parkgusmap.services.AvaliacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AvaliacaoServiceImpl implements AvaliacaoService {

    private final AvaliacaoRepository avaliacaoRepository;
    private final UsuarioAvaliadorRepository usuarioAvaliadorRepository;
    private final LocalRepository localRepository;

    @Override
    public Avaliacao criarAvaliacao(Long avaliadorId, Long localId, int nota) {
        UsuarioAvaliador avaliador = usuarioAvaliadorRepository.findById(avaliadorId)
                .orElseThrow(() -> new IllegalArgumentException("Avaliador não encontrado"));

        Local local = localRepository.findById(localId)
                .orElseThrow(() -> new IllegalArgumentException("Local não encontrado"));

        if (nota < 1 || nota > 5) {
            throw new IllegalArgumentException("A avaliação deve ser entre 1 e 5");
        }

        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setUsuario(avaliador);
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
            throw new RuntimeException("Avaliação não encontrada com id: " + id);
        }
        avaliacao.setId(id);
        return avaliacaoRepository.save(avaliacao);
    }

    @Override
    public void deleteById(Long id) {
        if (!avaliacaoRepository.existsById(id)) {
            throw new RuntimeException("Avaliação não encontrada com id: " + id);
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
        return avaliacaoRepository.findByUsuarioIdAndLocalId(usuarioId, localId);
    }


}