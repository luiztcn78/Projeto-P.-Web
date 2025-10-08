package br.upe.parkgusmap.entities.DTOs;

import br.upe.parkgusmap.entities.Evento;
import br.upe.parkgusmap.entities.Usuario;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EventoDTO {
    private Long id;

    @NotBlank(message = "Nome do evento é obrigatório")
    @Size(min = 2, max = 100, message = "Nome deve ter entre 2 e 100 caracteres")
    private String nome;

    @Size(max = 1024, message = "Descrição deve ter no máximo 1024 caracteres")
    private String descricao;

    @NotNull(message = "Data de início é obrigatória")
    @Future(message = "Data de início deve ser no futuro")
    private LocalDateTime dataInicio;

    @NotNull(message = "Data de fim é obrigatória")
    @Future(message = "Data de fim deve ser no futuro")
    private LocalDateTime dataFim;

    @Size(max = 512, message = "Endereço deve ter no máximo 512 caracteres")
    private String endereco;

    private List<String> imagens;

    @NotNull(message = "ID do local é obrigatório")
    private Long localId;

    @NotNull(message = "Lista de administradores é obrigatória")
    private List<Long> administradoresIds;

    public EventoDTO(Evento evento) {
        this.id = evento.getId();
        this.nome = evento.getNome();
        this.descricao = evento.getDescricao();
        this.dataInicio = evento.getDataHoraInicio();
        this.dataFim = evento.getDataHoraFim();
        this.endereco = evento.getEndereco();
        this.imagens = evento.getImagens();
        this.localId = evento.getLocal().getId();
        this.administradoresIds = evento.getAdministradores()
                .stream()
                .map(Usuario::getId)
                .toList();
    }

    public List<EventoDTO> transformarListaDTO(List<Evento> eventos){

        List<EventoDTO> eventosdto = new ArrayList<>();
        for(Evento evento : eventos){
            EventoDTO eventoDTO = new EventoDTO(evento);
            eventosdto.add(eventoDTO);
        }
        return eventosdto;
    }
}