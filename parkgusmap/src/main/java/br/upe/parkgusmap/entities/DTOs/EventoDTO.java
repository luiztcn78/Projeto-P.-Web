package br.upe.parkgusmap.entities.DTOs;

import br.upe.parkgusmap.entities.Evento;
import br.upe.parkgusmap.entities.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
}
