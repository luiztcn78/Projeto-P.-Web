package br.upe.parkgusmap.Exeptions;

public class EventoNaoEncontradoException extends RuntimeException{
    public EventoNaoEncontradoException(String mensagem){
        super(mensagem);
    }

    public EventoNaoEncontradoException(Long id){
        super("Bad Request - Evento não encontrado com o id: " + id);
    }
}
