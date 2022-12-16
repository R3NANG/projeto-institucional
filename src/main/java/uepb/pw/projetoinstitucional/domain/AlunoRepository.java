package uepb.pw.projetoinstitucional.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlunoRepository extends JpaRepository<Aluno, Integer> {

    List<Aluno> findByMatricula(Integer matricula);
}
