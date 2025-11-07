package br.upe.parkgusmap.Exeptions;

public class EventoNaoEncontradoException extends RuntimeException{
    public EventoNaoEncontradoException(String mensagem){
        super(mensagem);
    }

    public EventoNaoEncontradoException(){
        super("Bad Request - Evento não encontrado");
    }
}
