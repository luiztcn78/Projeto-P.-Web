package br.upe.parkgusmap.Exeptions;

public class EmailJaCadastradoException extends RuntimeException {
    public EmailJaCadastradoException(String message) {
        super(message);
    }

    public EmailJaCadastradoException() {
        super("Bad Request - Esse e-mail já está cadastrado no sistema");
    }
}
