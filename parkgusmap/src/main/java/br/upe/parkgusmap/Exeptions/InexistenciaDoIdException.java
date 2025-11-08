package br.upe.parkgusmap.Exeptions;

public class InexistenciaDoIdException extends RuntimeException{
    public InexistenciaDoIdException(String message){
        super(message);
    }

    public InexistenciaDoIdException(Long id){
        super("Bad Request - Este id: " + id + " não existe no sistema");
    }
}
