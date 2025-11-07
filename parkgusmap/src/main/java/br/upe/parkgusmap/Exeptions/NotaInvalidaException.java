package br.upe.parkgusmap.Exeptions;

public class NotaInvalidaException extends RuntimeException {
    public NotaInvalidaException(String message) {
        super(message);
    }

    public NotaInvalidaException() {
        super("Bad Request - Nota Inválida");
    }
}
