package uepb.pw.projetoinstitucional.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uepb.pw.projetoinstitucional.domain.Aluno;
import uepb.pw.projetoinstitucional.domain.AlunoDTO;
import uepb.pw.projetoinstitucional.domain.AlunoService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/alunos")
public class AlunosController {

    @Autowired
    private AlunoService alunoService;

    @GetMapping
    public ResponseEntity<List<AlunoDTO>> getAll() {
        return ResponseEntity.ok(alunoService.getAllAlunos());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Aluno> getById(@PathVariable("id") Integer id) {
        Optional<Aluno> optionalAluno = alunoService.getAlunoById(id);
        return optionalAluno.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/matricula/{matricula}")
    public ResponseEntity<List<AlunoDTO>> getByMatricula(@PathVariable("matricula") Integer matricula) {
        List<AlunoDTO> alunoList = alunoService.getAlunoByMatricula(matricula);
        return alunoList.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(alunoList);
    }

    @PostMapping
    public String create(@RequestBody Aluno aluno) {
        Aluno alunoCreated = alunoService.create(aluno);
        return "Aluno " + alunoCreated.getNome() + " com Id " + alunoCreated.getId() + " criado com sucesso!";
    }

    @PutMapping("/id/{id}")
    public String update(@PathVariable("id") Integer id, @RequestBody Aluno aluno) {
        Aluno alunoUpdated = alunoService.updateById(aluno, id);
        return "Aluno " + alunoUpdated.getNome() + " com Id " + alunoUpdated.getId() + " atualizado com sucesso!";
    }

    @DeleteMapping("/id/{id}")
    public void delete(@PathVariable("id") Integer id) {
        alunoService.deleteById(id);
    }
}
