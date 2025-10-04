package br.upe.parkgusmap.entities.DTOs;

import br.upe.parkgusmap.entities.Local;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LocalDTO {
    private Long id;
    private String nome;
    private String endereco;
    private String descricao;
    private String informacoes;
    private List<String> imagens;
    private List<String> especificacoes;
    private Long administradorId;

    public LocalDTO(Local local) {
        this.id = local.getId();
        this.nome = local.getNome();
        this.endereco = local.getEndereco();
        this.descricao = local.getDescricao();
        this.informacoes = local.getInformacoes();
        this.imagens = local.getImagens();
        this.especificacoes = local.getEspecificacoes();
        this.administradorId = local.getAdministrador().getId();
    }
}