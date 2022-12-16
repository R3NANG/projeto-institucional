package uepb.pw.projetoinstitucional.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "endereco")
@Data
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "rua", length = 255)
    private String rua;

    @Column(name = "numero", length = 8)
    private String numero;

    @Column(name = "cep", length = 14)
    private String cep;

    @Column(name = "cidade", length = 50)
    private String cidade;

    @Column(name = "estado", length = 50)
    private String estado;

    @Column(name = "pais", length = 50)
    private String pais;

    @OneToOne(mappedBy = "endereco")
    private Professor professor;

    @OneToOne(mappedBy = "endereco")
    private Aluno aluno;
}
