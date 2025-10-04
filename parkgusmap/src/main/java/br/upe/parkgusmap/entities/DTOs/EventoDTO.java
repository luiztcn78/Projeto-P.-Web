package br.upe.parkgusmap.entities.DTOs;

import br.upe.parkgusmap.entities.Evento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EventoDTO {
    private Long id;
    private String nome;
    private String descricao;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
    private String endereco;
    private List<String> imagens;
    private Long localId;
    private Long adminId;

    EventoDTO(Evento evento) {
        this.id = evento.getId();
        this.nome = evento.getNome();
        this.descricao = evento.getDescricao();
        this.dataInicio = evento.getDataHoraInicio();
        this.dataFim = evento.getDataHoraFim();
        this.endereco = evento.getEndereco();
        this.imagens = evento.getImagens();
        this.localId = evento.getLocal().getId();
        this.adminId = evento.getAdministrador().getId();
    }
}
