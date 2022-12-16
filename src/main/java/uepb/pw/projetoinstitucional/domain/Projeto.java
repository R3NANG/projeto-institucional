package uepb.pw.projetoinstitucional.domain;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity(name = "projeto")
@Data
public class Projeto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "titulo", length = 255)
    private String titulo;

    @Column(name = "area", length = 255)
    private String area;

    @Column(name = "resumo", length = 3000)
    private String resumo;

    @Column(name = "keyword1", length = 50)
    private String keyword1;

    @Column(name = "keyword2", length = 50)
    private String keyword2;

    @Column(name = "keyword3", length = 50)
    private String keyword3;

    @Column(name = "urlDocumento", length = 255)
    private String urlDocumento;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idProfessor", referencedColumnName = "id")
    private Professor professor;

    @OneToMany()
    private List<Aluno> aluno;
}
