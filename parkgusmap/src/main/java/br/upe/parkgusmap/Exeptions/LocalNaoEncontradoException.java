package br.upe.parkgusmap.Exeptions;

public class LocalNaoEncontradoException extends RuntimeException {
    public LocalNaoEncontradoException(String message) {
        super(message);
    }

    public LocalNaoEncontradoException(){
        super("Bad Request - Local não encontrado");
    }
}
