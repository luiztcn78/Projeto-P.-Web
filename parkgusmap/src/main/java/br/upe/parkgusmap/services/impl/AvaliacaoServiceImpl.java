package br.upe.parkgusmap.services.impl;

import br.upe.parkgusmap.entities.Avaliacao;
import br.upe.parkgusmap.entities.Local;
import br.upe.parkgusmap.entities.UsuarioAvaliador;
import br.upe.parkgusmap.repositories.AvaliacaoRepository;
import br.upe.parkgusmap.repositories.ComentarioRepository;
import br.upe.parkgusmap.repositories.LocalRepository;
import br.upe.parkgusmap.repositories.UsuarioAvaliadorRepository;
import br.upe.parkgusmap.services.AvaliacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AvaliacaoServiceImpl implements AvaliacaoService {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @Autowired
    private UsuarioAvaliadorRepository usuarioAvaliadorRepository;

    @Autowired
    private LocalRepository localRepository;

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
        avaliacao.setAvaliador(avaliador);
        avaliacao.setLocal(local);
        avaliacao.setAvaliacao(nota);

        //Lombok não ta criando os setters e getters!!

        return avaliacaoRepository.save(avaliacao);
    }

}
