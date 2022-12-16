package uepb.pw.projetoinstitucional.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjetoRepository extends JpaRepository<Projeto, Integer> {

    List<Projeto> findByTitulo(String titulo);
}
