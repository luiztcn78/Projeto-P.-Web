package br.upe.parkgusmap.services;

import br.upe.parkgusmap.entities.Comentario;

public interface ComentarioService {

    public Comentario criarComentario(Long avaliadorId, Long localId, String texto);
}
