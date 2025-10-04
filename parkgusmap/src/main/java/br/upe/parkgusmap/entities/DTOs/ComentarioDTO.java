package br.upe.parkgusmap.entities.DTOs;

import br.upe.parkgusmap.entities.Comentario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ComentarioDTO {
    private Long id;
    private String texto;
    private Long usuarioId;
    private Long localId;

    public ComentarioDTO(Comentario comentario) {
        this.id = comentario.getId();
        this.texto = comentario.getTexto();
        this.usuarioId = comentario.getUsuario().getId();
        this.localId = comentario.getLocal().getId();
    }
}