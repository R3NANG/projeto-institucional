package uepb.pw.projetoinstitucional.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProfessorRepository extends JpaRepository<Professor, Integer> {

    List<Professor> findByMatricula(Integer matricula);
}
