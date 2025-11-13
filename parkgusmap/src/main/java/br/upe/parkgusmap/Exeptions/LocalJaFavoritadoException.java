package br.upe.parkgusmap.Exeptions;

public class LocalJaFavoritadoException extends RuntimeException {
    public LocalJaFavoritadoException(String message) {
        super(message);
    }

    public LocalJaFavoritadoException(Long id) {
        super("O local de id: " + id + ", já foi favoritado");
    }
}
