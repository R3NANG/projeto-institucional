package uepb.pw.projetoinstitucional.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "aluno")
@Data
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "matricula")
    private Integer matricula;

    @Column(name = "nome", length = 255)
    private String nome;

    @Column(name = "cpf", length = 13)
    private String cpf;

    @Column(name = "curso", length = 255)
    private String curso;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idEndereco", referencedColumnName = "id")
    private Endereco endereco;

    @ManyToOne
    @JoinColumn(name = "projetoId")
    private Projeto projeto;
}
