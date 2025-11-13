package br.upe.parkgusmap.Exeptions;

public class DenunciaInexistenteException extends RuntimeException {
    public DenunciaInexistenteException(String message) {
        super(message);
    }

    public DenunciaInexistenteException() {
        super("Not Found - A denúncia não está cadastrada no sistema");
    }
}
