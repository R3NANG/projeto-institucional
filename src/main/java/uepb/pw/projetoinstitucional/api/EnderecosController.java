package uepb.pw.projetoinstitucional.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uepb.pw.projetoinstitucional.domain.Aluno;
import uepb.pw.projetoinstitucional.domain.Endereco;
import uepb.pw.projetoinstitucional.domain.EnderecoService;
import uepb.pw.projetoinstitucional.domain.Professor;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/enderecos")
public class EnderecosController {

    @Autowired
    private EnderecoService enderecoService;

    private ResponseEntity<Endereco> getEnderecoById(Integer id) {
        Optional<Endereco> optionalEndereco = enderecoService.getEnderecoById(id);
        return optionalEndereco.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/professor/{id}")
    public ResponseEntity<Endereco> getEnderecoByProfessorId(@PathVariable("id") Integer id, @RequestBody Endereco endereco) {
        Optional<Professor> optionalProfessor = enderecoService.getProfessorById(id);
        return optionalProfessor.isPresent() ?
                getEnderecoById(optionalProfessor.get().getId()) :
                ResponseEntity.notFound().build();
    }

    @GetMapping("/aluno/{id}")
    public ResponseEntity<Endereco> getEnderecoByAlunoId(@PathVariable("id") Integer id, @RequestBody Endereco endereco) {
        Optional<Aluno> optionalAluno = enderecoService.getAlunoById(id);
        return optionalAluno.isPresent() ?
                getEnderecoById(optionalAluno.get().getId()) :
                ResponseEntity.notFound().build();
    }

    @PostMapping
    public String createEndereco(@RequestBody Endereco endereco) {
        Endereco enderecoCreated = enderecoService.create(endereco);
        return "Endereço com Id " + enderecoCreated.getId() + " criado com sucesso!";
    }

    @PutMapping("/aluno/{id}")
    public String updateEnderecoByAlunoId(@PathVariable("id") Integer id, @RequestBody Endereco endereco) {
        Endereco enderecoUpdated = enderecoService.updateEnderecoAluno(endereco, id);
        return "Endereco com Id " + enderecoUpdated.getId() + " atualizado para o aluno com sucesso!";
    }

    @PutMapping("/professor/{id}")
    public String updateEnderecoByProfessorId(@PathVariable("id") Integer id, @RequestBody Endereco endereco) {
        Endereco enderecoUpdated = enderecoService.updateEnderecoProfessor(endereco, id);
        return "Endereco com Id " + enderecoUpdated.getId() + " atualizado para o professor com sucesso!";
    }

    @DeleteMapping("/id/{id}")
    public void deleteEndereco(@PathVariable("id") Integer id) {
        enderecoService.deleteById(id);
    }
}
