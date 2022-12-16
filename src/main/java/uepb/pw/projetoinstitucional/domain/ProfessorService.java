package uepb.pw.projetoinstitucional.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;
    @Autowired
    private EnderecoService enderecoService;

    public List<ProfessorDTO> getAllProfessores() {
        return professorRepository.findAll().stream().map(ProfessorDTO::new).collect(Collectors.toList());
    }

    public Optional<Professor> getProfessorById(Integer id) {
        return professorRepository.findById(id);
    }

    public List<ProfessorDTO> getProfessorByMatricula(Integer matricula) {
        return professorRepository.findByMatricula(matricula).stream().map(ProfessorDTO::new).collect(Collectors.toList());
    }

    public Professor create(Professor professor) {
        return professorRepository.save(professor);
    }

    public Professor updateById(Professor professor, Integer id) {
        Optional<Professor> optionalProfessor = getProfessorById(id);
        if (optionalProfessor.isPresent()) {
            Professor professorBD = optionalProfessor.get();
            professorBD.setMatricula(professor.getMatricula());
            professorBD.setNome(professor.getNome());
            professorBD.setCurso(professor.getCurso());
            enderecoService.updateEnderecoProfessor(professor.getEndereco(), id);
            professorRepository.save(professorBD);
            return professorBD;
        } else {
            throw new RuntimeException("Professor não encontrado");
        }
    }

    public void deleteById(Integer id) {
        Optional<Professor> optionalProfessor = getProfessorById(id);
        if (optionalProfessor.isPresent()) {
            professorRepository.deleteById(id);
        } else {
            throw new RuntimeException("Professor não encontrado");
        }
    }
}
