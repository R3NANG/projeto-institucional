package uepb.pw.projetoinstitucional.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private AlunoRepository alunoRepository;
    @Autowired
    private ProfessorRepository professorRepository;

    public ResponseEntity<List<EnderecoDTO>> getAllEnderecos() {
        return ResponseEntity.ok(enderecoRepository.findAll().stream().map(EnderecoDTO::new).collect(Collectors.toList()));
    }

    public Optional<Endereco> getEnderecoById(Integer id) {
        return enderecoRepository.findById(id);
    }

    public Optional<Aluno> getAlunoById(Integer id) {
        return alunoRepository.findById(id);
    }

    public Optional<Professor> getProfessorById(Integer id) {
        return professorRepository.findById(id);
    }

    public Endereco create(Endereco endereco) {
        return enderecoRepository.save(endereco);
    }

    private Endereco updateById(Endereco endereco, Integer id) {
        Optional<Endereco> optionalEndereco = enderecoRepository.findById(id);
        if (optionalEndereco.isPresent()) {
            Endereco enderecoBD = optionalEndereco.get();
            enderecoBD.setRua(endereco.getRua());
            enderecoBD.setNumero(endereco.getNumero());
            enderecoBD.setCep(endereco.getCep());
            enderecoBD.setCidade(endereco.getCidade());
            enderecoBD.setEstado(endereco.getEstado());
            enderecoBD.setPais(endereco.getPais());
            return enderecoRepository.save(enderecoBD);
        } else {
            throw new RuntimeException("Endereço não encontrado");
        }
    }

    public Endereco updateEnderecoAluno(Endereco endereco, Integer id) {
        Optional<Aluno> optionalAluno = getAlunoById(id);
        if (optionalAluno.isPresent()) {
            Integer idEndereco = optionalAluno.get().getEndereco().getId();
            return updateById(endereco, idEndereco);
        } else {
            throw new RuntimeException("Aluno não encontrado");
        }
    }

    public Endereco updateEnderecoProfessor(Endereco endereco, Integer id) {
        Optional<Professor> optionalProfessor = getProfessorById(id);
        if (optionalProfessor.isPresent()) {
            Integer idEndereco = optionalProfessor.get().getEndereco().getId();
            return updateById(endereco, idEndereco);
        } else {
            throw new RuntimeException("Professor não encontrado");
        }
    }

    public void deleteById(Integer id) {
        Optional<Endereco> optionalEndereco = getEnderecoById(id);
        if (optionalEndereco.isPresent()) {
            enderecoRepository.deleteById(id);
        } else {
            throw new RuntimeException("Endereço não encontrado");
        }
    }
}
