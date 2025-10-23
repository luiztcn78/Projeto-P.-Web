package br.upe.parkgusmap.entities;

import br.upe.parkgusmap.entities.Enums.SituacaoDenuncia;
import br.upe.parkgusmap.entities.Enums.TipoDenuncia;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Denuncia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private TipoDenuncia tipo;
    @Column(nullable = false)
    private String descricao;
    @Column(nullable = false)
    private Long idDenunciado;
    @Column(nullable = false)
    private SituacaoDenuncia situacao;

    public Denuncia(TipoDenuncia tipo, String descricao) {
        this.tipo = tipo;
        this.descricao = descricao;
        this.situacao = SituacaoDenuncia.PENDENTE;
    }
}
