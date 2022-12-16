package uepb.pw.projetoinstitucional.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "professor")
@Data
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "matricula")
    private Integer matricula;

    @Column(name = "nome", length = 255)
    private String nome;

    @Column(name = "curso", length = 255)
    private String curso;

    @OneToOne(mappedBy = "professor")
    private Projeto projeto;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idEndereco", referencedColumnName = "id")
    private Endereco endereco;
}
