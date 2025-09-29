package br.upe.parkgusmap.services;

import br.upe.parkgusmap.entities.Avaliacao;

public interface AvaliacaoService {
    public Avaliacao criarAvaliacao(Long avaliadorId, Long localId, int nota);
}
