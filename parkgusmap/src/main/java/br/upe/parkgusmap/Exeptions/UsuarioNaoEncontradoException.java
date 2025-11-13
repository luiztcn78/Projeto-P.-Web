package br.upe.parkgusmap.Exeptions;

public class UsuarioNaoEncontradoException extends RuntimeException {
    public UsuarioNaoEncontradoException(Long id) {
        super("Not Found - Usuário avaliador não encontrado com o id: " + id);
    }

    public UsuarioNaoEncontradoException(String email) {
        super("Not Found - Usuário avaliador não encontrado com o e-mail: " + email);
    }
}
