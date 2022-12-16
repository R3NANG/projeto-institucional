package uepb.pw.projetoinstitucional.domain;

import lombok.Data;

@Data
public class ProfessorDTO {

    private Integer id;
    private Integer matricula;
    private String nome;
    private String curso;
    private Endereco endereco;

    public ProfessorDTO(Professor professor) {
        this.id = professor.getId();
        this.matricula = professor.getMatricula();
        this.nome = professor.getNome();
        this.curso = professor.getCurso();
        this.endereco = professor.getEndereco();
    }
}
