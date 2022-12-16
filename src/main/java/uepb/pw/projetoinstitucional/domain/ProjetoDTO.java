package uepb.pw.projetoinstitucional.domain;

import lombok.Data;

import java.util.List;

@Data
public class ProjetoDTO {

    private Integer id;
    private String titulo;
    private String area;
    private String resumo;
    private String keyword1;
    private String keyword2;
    private String keyword3;
    private String urlDocumento;
    private Professor professor;
    private List<Aluno> alunos;

    public ProjetoDTO(Projeto projeto) {
        this.id = projeto.getId();
        this.titulo = projeto.getTitulo();
        this.area = projeto.getArea();
        this.resumo = projeto.getResumo();
        this.keyword1 = projeto.getKeyword1();
        this.keyword2 = projeto.getKeyword2();
        this.keyword3 = projeto.getKeyword3();
        this.urlDocumento = projeto.getUrlDocumento();
        this.professor = projeto.getProfessor();
        this.alunos = projeto.getAlunos();
    }
}
