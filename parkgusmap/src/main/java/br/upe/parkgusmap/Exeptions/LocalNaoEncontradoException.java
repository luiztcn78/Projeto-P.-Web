package br.upe.parkgusmap.Exeptions;

public class LocalNaoEncontradoException extends RuntimeException {
    public LocalNaoEncontradoException(String message) {
        super(message);
    }

    public LocalNaoEncontradoException(Long id) {
        super("Not Found - Local não encontrado com o id: " + id);
    }
}
