package br.upe.parkgusmap.entities.DTOs;


import br.upe.parkgusmap.entities.Avaliacao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AvaliacaoDTO {

    private Long id;
    private Integer nota;
    private Long usuarioId;
    private Long localId;

    public AvaliacaoDTO(Avaliacao avaliacao) {
            this.id = avaliacao.getId();
            this.nota = avaliacao.getNota();
            if(avaliacao.getUsuario() != null) {
                this.usuarioId = avaliacao.getUsuario().getId();
            }
            if(avaliacao.getLocal() != null) {
                this.localId = avaliacao.getLocal().getId();
            }
    }
}
