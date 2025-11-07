package br.upe.parkgusmap.Exeptions;

public class ComentarioInvalidoException extends RuntimeException {
    public ComentarioInvalidoException(String message) {
        super(message);
    }

    public ComentarioInvalidoException() {
    super("Bad Request - Tipo de comentário Invalido (O comentário não pode ser vazio)");
    }
}
