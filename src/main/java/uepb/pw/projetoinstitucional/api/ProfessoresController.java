package uepb.pw.projetoinstitucional.api;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uepb.pw.projetoinstitucional.domain.Professor;
import uepb.pw.projetoinstitucional.domain.ProfessorDTO;
import uepb.pw.projetoinstitucional.domain.ProfessorService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/professores")
public class ProfessoresController {

    @Autowired
    private ProfessorService professorService;

    @GetMapping
    public ResponseEntity<List<ProfessorDTO>> getAll() {
        return ResponseEntity.ok(professorService.getAllProfessores());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Professor> getById(@PathVariable("id") Integer id) {
        Optional<Professor> optionalProfessor = professorService.getProfessorById(id);
        return optionalProfessor.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/matricula/{matricula}")
    public ResponseEntity<List<ProfessorDTO>> getProfessorByMatricula(@PathVariable("matricula") Integer matricula) {
        List<ProfessorDTO> professorList = professorService.getProfessorByMatricula(matricula);
        return professorList.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(professorList);
    }

    @PostMapping
    public String createProfessor(@RequestBody Professor professor) {
        Professor professorCreated = professorService.create(professor);
        return "Professor " + professorCreated.getNome() + " com Id " + professorCreated.getId() + " criado com sucesso!";
    }

    @PutMapping("/id/{id}")
    public String updateProfessor(@PathVariable("id") Integer id, @RequestBody Professor professor) {
        Professor professorUpdated = professorService.updateById(professor, id);
        return "Professor " + professorUpdated.getNome() + " com Id " + professorUpdated.getId() + " atualizado com sucesso!";
    }

    @DeleteMapping("/id/{id}")
    public void deleteProfessor(@PathVariable("id") Integer id) {
        professorService.deleteById(id);
    }
}
