package br.upe.parkgusmap.services;

import br.upe.parkgusmap.entities.Comentario;
import br.upe.parkgusmap.entities.Denuncia;

import java.util.List;

public interface DenunciaService {
    void fazerDenuncia(Denuncia denuncia);
    List<Denuncia> listarTodasDenuncias();
    Comentario encontrarComentarioDenunciado(Long idDenuncia);
}
