package br.upe.parkgusmap.entities.DTOs;

import br.upe.parkgusmap.entities.Enums.Perfil;
import br.upe.parkgusmap.entities.Local;
import br.upe.parkgusmap.entities.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UsuarioResponsivoDTO {

    private Long id;
    private String nome;
    private String email;
    private Perfil perfil;
    private List<LocalDTO> locaisFavoritos;

    public UsuarioResponsivoDTO(Usuario usuario) {
        this.id = usuario.getId();
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
        this.perfil = usuario.getPerfil();
        if(usuario.getLocaisFavoritos()!=null){
            this.locaisFavoritos = ListLocalDTO(usuario.getLocaisFavoritos());
        }
    }

    public List<LocalDTO> ListLocalDTO(List<Local> locais) {
        List<LocalDTO> localDTOs = new ArrayList<>();
        for (Local local : locais) {
            LocalDTO localDTO = new LocalDTO(local);
            localDTOs.add(localDTO);
        }
        return localDTOs;
    }
}
