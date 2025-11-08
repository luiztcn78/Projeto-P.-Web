package br.upe.parkgusmap.Exeptions;

public class AvaliacaoNaoEncontradaException extends RuntimeException {
    public AvaliacaoNaoEncontradaException(String message) {
        super(message);
    }

    public AvaliacaoNaoEncontradaException(Long id) {
        super("Bad Request - Avaliação não encontrada no id: " + id);
    }
}
