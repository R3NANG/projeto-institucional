package uepb.pw.projetoinstitucional.api;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uepb.pw.projetoinstitucional.domain.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/projetos")
public class ProjetosController {

    @Autowired
    private ProjetoService projetoService;
    @Autowired
    private ProfessorService professorService;
    @Autowired
    private AlunoService alunoService;

    @GetMapping
    public ResponseEntity<List<ProjetoDTO>> getAll() {
        return ResponseEntity.ok(projetoService.getAllProjetos());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Projeto> getById(@PathVariable("id") Integer id) {
        Optional<Projeto> optionalProjeto = projetoService.getProjetoById(id);
        return optionalProjeto.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<List<ProjetoDTO>> getByTitulo(@PathVariable("titulo") String titulo) {
        List<ProjetoDTO> projetoList = projetoService.getProjetoByTitulo(titulo);
        return projetoList.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(projetoList);
    }

    @PostMapping
    public String createProjeto(@RequestBody ProjetoDTO projetoDTO) {
        Optional<Professor> optionalProfessor = professorService.getProfessorById(projetoDTO.getId());
        List<Aluno> alunos = alunoService.getAlunosById(projetoDTO.getAlunosId());

        if (optionalProfessor.isPresent() && !alunos.isEmpty()) {
            Projeto projeto = new Projeto();
            projeto.setTitulo(projetoDTO.getTitulo());
            projeto.setArea(projetoDTO.getArea());
            projeto.setResumo(projetoDTO.getResumo());
            projeto.setKeyword1(projetoDTO.getKeyword1());
            projeto.setKeyword2(projetoDTO.getKeyword2());
            projeto.setKeyword3(projetoDTO.getKeyword3());
            projeto.setUrlDocumento(projetoDTO.getUrlDocumento());
            projeto.setAlunos(alunos);
            projeto.setProfessor(optionalProfessor.get());
            return "Projeto com Id " + projetoService.create(projeto).getId() + " criado com sucesso!";
        } else {
            return "Projeto não criado!";
        }
    }

    @PutMapping("/id/{id}/addAluno/{alunoId}")
    public String addAluno(@PathVariable("id") Integer id, @PathVariable("alunoId") Integer alunoId) {
        Optional<Projeto> optionalProjeto = projetoService.getProjetoById(id);
        if (!optionalProjeto.isPresent()) return "Projeto não encontrado!";

        Optional<Aluno> optionalAluno = alunoService.getAlunoById(alunoId);
        if (!optionalAluno.isPresent()) return "Aluno não encontrado!";

        optionalProjeto.get().getAlunos().add(optionalAluno.get());
        List<Integer> ids = projetoService.updateById(optionalProjeto.get(), id).getAlunos().stream().map(Aluno::getId).collect(Collectors.toList());
        return "Aluno adicionado ao projeto: " + ids;
    }

    @PostMapping("/id/{id}/addAlunos")
    public String addAlunos(@PathVariable("id") Integer id, @RequestBody AlunosDTO alunosDTO) {
        Optional<Projeto> optionalProjeto = projetoService.getProjetoById(id);
        if (!optionalProjeto.isPresent()) return "Projeto não encontrado!";

        StringBuffer erro = new StringBuffer("Os seguintes alunos não foram adicionados: ");
        for (int i = 0; i < alunosDTO.getIds().size(); i++) {
            Integer alunoId = alunosDTO.getIds().get(i);
            Optional<Aluno> optionalAluno = alunoService.getAlunoById(alunoId);
            if (!optionalAluno.isPresent()) {
                erro.append(alunoId);
            } else {
                optionalProjeto.get().getAlunos().add(optionalAluno.get());
            }
        }

        projetoService.updateById(optionalProjeto.get(), id);

        if (erro.length() != 0) {
            return erro.toString();
        } else {
            return "Todos os alunos foram adicionados ao projeto!";
        }
    }

    @PostMapping("/id/{id}/removerAluno/{alunoId}")
    public String removerAluno(@PathVariable("id") Integer id, @PathVariable("alunoId") Integer alunoId) {
        Optional<Projeto> optionalProjeto = projetoService.getProjetoById(id);
        if (!optionalProjeto.isPresent()) return "Projeto não encontrado!";

        Boolean removido = false;
        for (int i = 0; i < optionalProjeto.get().getAlunos().size(); i++) {
            Aluno aluno = optionalProjeto.get().getAlunos().get(i);
            if (aluno.getId().equals(alunoId)) {
                optionalProjeto.get().getAlunos().remove(i);
                removido = true;
                break;
            }
        }

        projetoService.updateById(optionalProjeto.get(), id);

        if (!removido) {
            return "Aluno não está alocado ao projeto!";
        } else {
            return "Aluno removido do projeto!";
        }
    }

    @PutMapping("/id/{id}")
    public String updateProjeto(@PathVariable("id") Integer id, @RequestBody Projeto projeto) {
        Projeto projetoUpdated = projetoService.updateById(projeto, id);
        return "Projeto com Id " + projetoUpdated.getId() + " atualizado com sucesso!";
    }

    @PutMapping("/id/{id}/professor/{professorId}")
    public String updateProfessorProjeto(@PathVariable("id") Integer id, @PathVariable("professorId") Integer professorId) {
        Projeto projetoUpdated = projetoService.updateProfessor(id, professorId);
        return "Projeto com Id " + projetoUpdated.getId() + " atualizado com sucesso!";
    }

    @DeleteMapping("/id/{id}")
    public void deleteProjeto(@PathVariable("id") Integer id) {
        projetoService.deleteById(id);
    }
}
