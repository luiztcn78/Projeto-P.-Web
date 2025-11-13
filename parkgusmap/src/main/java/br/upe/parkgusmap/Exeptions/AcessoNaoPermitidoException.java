package br.upe.parkgusmap.Exeptions;

public class AcessoNaoPermitidoException extends RuntimeException {
    public AcessoNaoPermitidoException(String message) {
        super(message);
    }

    public AcessoNaoPermitidoException() {
        super("Acesso Negado - Acesso não permitido (O usuário não é administrador)");
    }
}
