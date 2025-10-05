package br.upe.parkgusmap.entities.DTOs;

import br.upe.parkgusmap.entities.Local;
import br.upe.parkgusmap.entities.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

    @NotBlank(message = "Nome é obrigatório")
    @Size(min = 2, max = 100, message = "Nome deve ter entre 2 e 100 caracteres")
    private String nome;

    @NotBlank(message = "Endereço é obrigatório")
    @Size(max = 512, message = "Endereço deve ter no máximo 512 caracteres")
    private String endereco;

    @Size(max = 1024, message = "Descrição deve ter no máximo 1024 caracteres")
    private String descricao;

    @Size(max = 1024, message = "Informações devem ter no máximo 1024 caracteres")
    private String informacoes;

    private List<String> imagens;
    private List<String> especificacoes;

    @NotNull(message = "Lista de administradores é obrigatória")
    private List<Long> administradoresIds;

    public LocalDTO(Local local) {
        this.id = local.getId();
        this.nome = local.getNome();
        this.endereco = local.getEndereco();
        this.descricao = local.getDescricao();
        this.informacoes = local.getInformacoes();
        this.imagens = local.getImagens();
        this.especificacoes = local.getEspecificacoes();
        this.administradoresIds = local.getAdministradores().stream()
                .map(Usuario::getId)
                .toList();
    }
}