package br.upe.parkgusmap.Exeptions;

public class ComentarioNaoEncontradoException extends RuntimeException {
    public ComentarioNaoEncontradoException(String message) {
        super(message);
    }

    public ComentarioNaoEncontradoException(Long id) {
        super("Not Found - Comentário não Encontrado com o id: " + id);
    }
}
