package br.upe.parkgusmap.Exeptions;

public class TipoDeDenunciaIndefinidoException extends RuntimeException{
    public TipoDeDenunciaIndefinidoException(String mensage){
        super(mensage);
    }

    public TipoDeDenunciaIndefinidoException(){
        super("Bad Request - O tipo de denuncia não foi definido");
    }
}
