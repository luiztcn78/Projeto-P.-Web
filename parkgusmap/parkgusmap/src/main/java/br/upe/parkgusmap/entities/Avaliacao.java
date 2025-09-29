package br.upe.parkgusmap.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Avaliacao {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Min(1)
    @Max(5)
    @Column(nullable = false)
    private int avaliacao;  // corrigido de 'avalicao'

    @ManyToOne
    @JoinColumn(name = "usuariova_id", nullable = false)
    private UsuarioAvaliador avaliador;

    @ManyToOne
    @JoinColumn(name = "local_id", nullable = false)
    private Local local;

}


