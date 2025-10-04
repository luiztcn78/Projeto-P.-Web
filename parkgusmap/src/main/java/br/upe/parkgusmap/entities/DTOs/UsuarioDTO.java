package br.upe.parkgusmap.entities.DTOs;

import br.upe.parkgusmap.entities.Enums.Perfil;
import br.upe.parkgusmap.entities.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UsuarioDTO {

    private Long id;
    private String nome;
    private String email;
    private Perfil perfil;

    public UsuarioDTO(Usuario usuario) {
        this.id = usuario.getId();
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
        this.perfil = usuario.getPerfil();
    }
}
