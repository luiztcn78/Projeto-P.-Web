package br.upe.parkgusmap.Exeptions;

public class UsuarioNaoEncontrado extends RuntimeException {
    public UsuarioNaoEncontrado(String message) {
        super(message);
    }

    public UsuarioNaoEncontrado() {
        super("Bad Request - Usuário avaliador não encontrado.");
    }
}
