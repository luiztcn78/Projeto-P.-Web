package br.upe.parkgusmap.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Comentario {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(length=512)
    private String comentario;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private UsuarioAvaliador Avaliador;

    @ManyToOne
    @JoinColumn(name = "local_id", nullable = false)
    private Local local;
}
