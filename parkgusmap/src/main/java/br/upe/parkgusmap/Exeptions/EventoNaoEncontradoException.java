package br.upe.parkgusmap.Exeptions;

public class EventoNaoEncontradoException extends RuntimeException{
    public EventoNaoEncontradoException(String mensagem){
        super(mensagem);
    }

    public EventoNaoEncontradoException(Long id){
        super("Not Found - Evento não encontrado com o id: " + id);
    }
}
