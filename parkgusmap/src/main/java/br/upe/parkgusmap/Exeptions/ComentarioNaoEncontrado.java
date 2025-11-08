package br.upe.parkgusmap.Exeptions;

public class ComentarioNaoEncontrado extends RuntimeException {
    public ComentarioNaoEncontrado(String message) {
        super(message);
    }

    public ComentarioNaoEncontrado(Long id) {
        super("Bad Request - Comentário não Encontrado: " + id);
    }
}
