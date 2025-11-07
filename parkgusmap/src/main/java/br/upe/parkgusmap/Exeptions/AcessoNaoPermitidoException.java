package br.upe.parkgusmap.Exeptions;

public class AcessoNaoPermitidoException extends RuntimeException {
    public AcessoNaoPermitidoException(String message) {
        super(message);
    }

    public AcessoNaoPermitidoException() {
        super("Bad Request - Acesso não permitido (O usuário não é administrador)");
    }
}
