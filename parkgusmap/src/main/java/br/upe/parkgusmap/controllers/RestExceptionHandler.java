package br.upe.parkgusmap.controllers;


import br.upe.parkgusmap.Exeptions.*;
import br.upe.parkgusmap.entities.DTOs.ExceptionResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(LocalJaFavoritadoException.class)
    public ResponseEntity<ExceptionResponseDTO> handleLocalJaFavoritadoException(LocalJaFavoritadoException ljfe,  HttpServletRequest request) {
        ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(ljfe.getMessage(), 400, request.getRequestURI());
        return ResponseEntity.status(exceptionResponseDTO.getStatus()).body(exceptionResponseDTO);
    }

    @ExceptionHandler(AcessoNaoPermitidoException.class)
    public ResponseEntity<ExceptionResponseDTO>  handleAcessoNaoPermitidoException(AcessoNaoPermitidoException anpe, HttpServletRequest request){
        ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(anpe.getMessage(), 403,  request.getRequestURI());
        return ResponseEntity.status(exceptionResponseDTO.getStatus()).body(exceptionResponseDTO);
    }

    @ExceptionHandler(AvaliacaoNaoEncontradaException.class)
    public ResponseEntity<ExceptionResponseDTO> handleAvaliacaoNaoEncontradaException(AvaliacaoNaoEncontradaException anpe, HttpServletRequest request){
        ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(anpe.getMessage(), 404,  request.getRequestURI());
        return ResponseEntity.status(exceptionResponseDTO.getStatus()).body(exceptionResponseDTO);
    }

    @ExceptionHandler(ComentarioInvalidoException.class)
    public ResponseEntity<ExceptionResponseDTO> handleComentarioInvalidoException(ComentarioInvalidoException cie, HttpServletRequest request){
        ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(cie.getMessage(), 400,  request.getRequestURI());
        return ResponseEntity.status(exceptionResponseDTO.getStatus()).body(exceptionResponseDTO);
    }

    @ExceptionHandler(ComentarioNaoEncontradoException.class)
    public ResponseEntity<ExceptionResponseDTO> handleComentarioNaoEncontradoException(ComentarioNaoEncontradoException cnee, HttpServletRequest request){
        ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(cnee.getMessage(), 404,  request.getRequestURI());
        return ResponseEntity.status(exceptionResponseDTO.getStatus()).body(exceptionResponseDTO);
    }

    @ExceptionHandler(DenunciaInexistenteException.class)
    public ResponseEntity<ExceptionResponseDTO> handleDenunciaInexistenteException(DenunciaInexistenteException die, HttpServletRequest request){
        ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(die.getMessage(), 404,  request.getRequestURI());
        return ResponseEntity.status(exceptionResponseDTO.getStatus()).body(exceptionResponseDTO);
    }

    @ExceptionHandler(DescricaoInvalidaException.class)
    public ResponseEntity<ExceptionResponseDTO> handleDescricaoInvalidaException(DescricaoInvalidaException die, HttpServletRequest request){
        ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(die.getMessage(), 400,  request.getRequestURI());
        return ResponseEntity.status(exceptionResponseDTO.getStatus()).body(exceptionResponseDTO);
    }

    @ExceptionHandler(EmailJaCadastradoException.class)
    public ResponseEntity<ExceptionResponseDTO> handleEmailJaCadastradoException(EmailJaCadastradoException ejce, HttpServletRequest request){
        ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(ejce.getMessage(), 400,  request.getRequestURI());
        return ResponseEntity.status(exceptionResponseDTO.getStatus()).body(exceptionResponseDTO);
    }

    @ExceptionHandler(EventoNaoEncontradoException.class)
    public ResponseEntity<ExceptionResponseDTO> handleEventoNaoEncontradoException(EventoNaoEncontradoException enee, HttpServletRequest request){
        ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(enee.getMessage(), 404,  request.getRequestURI());
        return ResponseEntity.status(exceptionResponseDTO.getStatus()).body(exceptionResponseDTO);
    }

    @ExceptionHandler(InexistenciaDoIdException.class)
    public ResponseEntity<ExceptionResponseDTO> handleInexistenciaDoIdException(InexistenciaDoIdException idie, HttpServletRequest request){
        ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(idie.getMessage(), 404,  request.getRequestURI());
        return ResponseEntity.status(exceptionResponseDTO.getStatus()).body(exceptionResponseDTO);
    }

    @ExceptionHandler(LocalNaoEncontradoException.class)
    public ResponseEntity<ExceptionResponseDTO> handleLocalNaoEncontradoException(LocalNaoEncontradoException lnee, HttpServletRequest request){
        ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(lnee.getMessage(), 404,  request.getRequestURI());
        return ResponseEntity.status(exceptionResponseDTO.getStatus()).body(exceptionResponseDTO);
    }

    @ExceptionHandler(NomeDeUsuarioInvalidoException.class)
    public ResponseEntity<ExceptionResponseDTO> handleNomeDeUsuarioInvalidoException(NomeDeUsuarioInvalidoException nduie, HttpServletRequest request){
        ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(nduie.getMessage(), 400,  request.getRequestURI());
        return ResponseEntity.status(exceptionResponseDTO.getStatus()).body(exceptionResponseDTO);
    }

    @ExceptionHandler(NotaInvalidaException.class)
    public ResponseEntity<ExceptionResponseDTO> handleNotaInvalidaException(NotaInvalidaException nie, HttpServletRequest request){
        ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(nie.getMessage(), 404,  request.getRequestURI());
        return ResponseEntity.status(exceptionResponseDTO.getStatus()).body(exceptionResponseDTO);
    }

    @ExceptionHandler(ObjetoDenunciadoNaoComentarioException.class)
    public ResponseEntity<ExceptionResponseDTO> handleObjetoDenunciadoNaoComentarioException(ObjetoDenunciadoNaoComentarioException odnce, HttpServletRequest request){
        ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(odnce.getMessage(), 400,  request.getRequestURI());
        return ResponseEntity.status(exceptionResponseDTO.getStatus()).body(exceptionResponseDTO);
    }

    @ExceptionHandler(TipoDeDenunciaIndefinidoException.class)
    public ResponseEntity<ExceptionResponseDTO> handleTipoDeDenunciaIndefinidoException(TipoDeDenunciaIndefinidoException tdie, HttpServletRequest request){
        ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(tdie.getMessage(), 400,  request.getRequestURI());
        return ResponseEntity.status(exceptionResponseDTO.getStatus()).body(exceptionResponseDTO);
    }

    @ExceptionHandler(UsuarioNaoEncontradoException.class)
    public ResponseEntity<ExceptionResponseDTO> handleUsuarioNaoEncontradoException(UsuarioNaoEncontradoException unee, HttpServletRequest request){
        ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(unee.getMessage(), 404,  request.getRequestURI());
        return ResponseEntity.status(exceptionResponseDTO.getStatus()).body(exceptionResponseDTO);
    }

}
