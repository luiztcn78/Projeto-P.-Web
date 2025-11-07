package br.upe.parkgusmap.Exeptions;

public class ObjetoDenunciadoNaoComentarioException extends  RuntimeException{
    public ObjetoDenunciadoNaoComentarioException(String mensage){
        super(mensage);
    }

    public ObjetoDenunciadoNaoComentarioException(){
        super("Bad Request - O objeto denunciado não é um comentário");
    }
}
