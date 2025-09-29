package br.upe.parkgusmap.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Local {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, length = 512)
    private String endereco;

    @Column(length = 1024)
    private String descricao;

    @Column(length = 1024)
    private String informacoes;

    @ElementCollection
    @CollectionTable(name = "local_imagens", joinColumns = @JoinColumn(name = "local_id"))
    @Column(name = "imagem_url")
    private List<String> imagens;

    @ElementCollection
    @CollectionTable(name = "local_especificacoes", joinColumns = @JoinColumn(name = "local_id"))
    @Column(name = "especificacao")
    private List<String> especificacoes;

    @OneToMany(mappedBy = "local", cascade = CascadeType.ALL)
    private List<Comentario> comentarios = new ArrayList<>();

    @OneToMany(mappedBy = "local", cascade = CascadeType.ALL)
    private List<Avaliacao> avaliacoes = new ArrayList<>();

    //  Relacionamento com Evento
    @OneToMany(mappedBy = "local", cascade = CascadeType.ALL)
    private List<Evento> eventos = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "administrador_id", nullable = false)
    private UsuarioAdministrador administrador;
}