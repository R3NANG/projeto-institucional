package uepb.pw.projetoinstitucional.domain;

import lombok.Data;

@Data
public class AlunoDTO {

    private Integer id;
    private Integer matricula;
    private String nome;
    private String curso;
    private Endereco endereco;

    public AlunoDTO(Aluno aluno) {
        super();
        this.id = aluno.getId();
        this.matricula = aluno.getMatricula();
        this.nome = aluno.getNome();
        this.curso = aluno.getCurso();
        this.endereco = aluno.getEndereco();
    }
}
