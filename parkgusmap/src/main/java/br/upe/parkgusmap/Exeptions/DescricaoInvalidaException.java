package br.upe.parkgusmap.Exeptions;

public class DescricaoInvalidaException extends RuntimeException{
    public DescricaoInvalidaException(String mensage){
        super(mensage);
    }

    public DescricaoInvalidaException(){
        super("Bad Request - Descrição invalida (A descrição não pode ser vazia)");
    }
}
