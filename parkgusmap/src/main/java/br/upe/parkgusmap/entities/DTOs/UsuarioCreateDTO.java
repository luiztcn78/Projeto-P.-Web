package br.upe.parkgusmap.entities.DTOs;


import br.upe.parkgusmap.entities.Enums.Perfil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UsuarioCreateDTO {
    private String nome;
    private String email;
    private String senha;
    private Perfil perfil;

}
