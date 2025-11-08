package br.upe.parkgusmap.Exeptions;

public class UsuarioNaoEncontradoException extends RuntimeException {
    public UsuarioNaoEncontradoException(Long id) {
        super("Bad Request - Usuário avaliador não encontrado com o id: " + id);
    }

    public UsuarioNaoEncontradoException(String email) {
        super("Bad Request - Usuário avaliador não encontrado com o e-mail: " + email);
    }
}
