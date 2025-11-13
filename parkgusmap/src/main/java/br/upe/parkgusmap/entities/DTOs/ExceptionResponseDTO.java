package br.upe.parkgusmap.entities.DTOs;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ExceptionResponseDTO {
    
    private LocalDateTime tempo;
    private String mensagem;
    private int status;
    private String request;

    public ExceptionResponseDTO(String mensagem, int status, String request) {
        this.tempo = LocalDateTime.now();
        this.mensagem = mensagem;
        this.status = status;
        this.request = request;
    }

}
