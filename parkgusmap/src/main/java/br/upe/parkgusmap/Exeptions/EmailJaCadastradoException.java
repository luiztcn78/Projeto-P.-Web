package br.upe.parkgusmap.Exeptions;

public class EmailJaCadastradoException extends RuntimeException {

    public EmailJaCadastradoException(String email) {
        super("Bad Request - o e-mail: " + email + " já está cadastrado no sistema");
    }
}
