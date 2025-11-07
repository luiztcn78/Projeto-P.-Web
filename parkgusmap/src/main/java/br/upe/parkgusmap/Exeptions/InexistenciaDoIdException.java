package br.upe.parkgusmap.Exeptions;

public class InexistenciaDoIdException extends Exception{
    public InexistenciaDoIdException(String message){
        super(message);
    }

    public InexistenciaDoIdException(){
        super("Bad Request - Este id não existe no sistema");
    }
}
