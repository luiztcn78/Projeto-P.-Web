package br.upe.parkgusmap.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


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
    @Column()
    private int avalicao;


    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private UsuarioAvaliador usuario;
}
