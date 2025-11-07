package br.upe.parkgusmap.Exeptions;

public class NomeDeUsuarioInvalidoException extends RuntimeException {
    public NomeDeUsuarioInvalidoException(String mensage) {
        super(mensage);
    }

    public NomeDeUsuarioInvalidoException() {
        super("Bad Request - Nome de usuário inválido (O nome de usuário não pode ser vazio)");
    }
}
