package uepb.pw.projetoinstitucional.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;
    @Autowired
    private EnderecoService enderecoService;

    public List<AlunoDTO> getAllAlunos() {
        return alunoRepository.findAll().stream().map(AlunoDTO::new).collect(Collectors.toList());
    }

    public List<Aluno> getAlunosById(List<Integer> ids) {
        return alunoRepository.findAllById(ids);
    }

    public Optional<Aluno> getAlunoById(Integer id) {
        return alunoRepository.findById(id);
    }

    public List<AlunoDTO> getAlunoByMatricula(Integer matricula) {
        return alunoRepository.findByMatricula(matricula).stream().map(AlunoDTO::new).collect(Collectors.toList());
    }

    public Aluno create(Aluno aluno) {
        return alunoRepository.save(aluno);
    }

    public Aluno updateById(Aluno aluno, Integer id) {
        Optional<Aluno> optionalAluno = getAlunoById(id);
        if (optionalAluno.isPresent()) {
            Aluno alunoBD = optionalAluno.get();
            alunoBD.setMatricula(aluno.getMatricula());
            alunoBD.setNome(aluno.getNome());
            alunoBD.setCpf(aluno.getCpf());
            alunoBD.setCurso(aluno.getCurso());
            enderecoService.updateEnderecoAluno(aluno.getEndereco(), id);
            alunoRepository.save(alunoBD);

            return alunoBD;
        } else {
            throw new RuntimeException("Aluno não encontrado");
        }

    }

    public void deleteById(Integer id) {
        Optional<Aluno> alunoOptional = getAlunoById(id);
        if (alunoOptional.isPresent()) {
            alunoRepository.deleteById(id);
        } else {
            throw new RuntimeException("Aluno não encontrado");
        }
    }
}
