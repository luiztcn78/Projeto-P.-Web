package br.upe.parkgusmap.entities;

import br.upe.parkgusmap.entities.Enums.Perfil;
import jakarta.persistence.*;

public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String senha;

    @Enumerated(EnumType.STRING)
    private Perfil perfil;
}
