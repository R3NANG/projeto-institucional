package uepb.pw.projetoinstitucional.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProjetoService {

    @Autowired
    private ProjetoRepository projetoRepository;
    @Autowired
    private ProfessorRepository professorRepository;

    public List<ProjetoDTO> getAllProjetos() {
        return projetoRepository.findAll().stream().map(ProjetoDTO::new).collect(Collectors.toList());
    }

    public Optional<Projeto> getProjetoById(Integer id) {
        return projetoRepository.findById(id);
    }

    public List<ProjetoDTO> getProjetoByTitulo(String titulo) {
        return projetoRepository.findByTitulo(titulo).stream().map(ProjetoDTO::new).collect(Collectors.toList());
    }

    public Projeto create(Projeto projeto) {
        return projetoRepository.save(projeto);
    }

    public Projeto updateById(Projeto projeto, Integer id) {
        Optional<Projeto> optionalProjeto = getProjetoById(id);
        if (optionalProjeto.isPresent()) {
            Projeto projetoBD = optionalProjeto.get();
            projetoBD.setTitulo(projeto.getTitulo());
            projetoBD.setArea(projeto.getArea());
            projetoBD.setResumo(projeto.getResumo());
            projetoBD.setKeyword1(projeto.getKeyword1());
            projetoBD.setKeyword2(projeto.getKeyword2());
            projetoBD.setKeyword3(projeto.getKeyword3());
            projetoBD.setUrlDocumento(projeto.getUrlDocumento());
            projetoBD.setProfessor(projeto.getProfessor());
            projetoBD.setAlunos(projeto.getAlunos());
            projetoRepository.save(projetoBD);
            return projetoBD;
        } else {
            throw new RuntimeException("Projeto não encontrado");
        }
    }

    public Optional<Professor> getProfessorById(Integer id) {
        return professorRepository.findById(id);
    }

    public Projeto updateProfessor(Integer idProjeto, Integer idProfessor) {
        Optional<Projeto> optionalProjeto = getProjetoById(idProjeto);
        if (optionalProjeto.isPresent()) {
            Projeto projetoBD = optionalProjeto.get();
            Optional<Professor> optionalProfessor = getProfessorById(idProfessor);
            projetoBD.setProfessor(optionalProfessor.get());
            projetoRepository.save(projetoBD);
            return projetoBD;
        } else {
            throw new RuntimeException("Projeto não encontrado");
        }
    }

    public void deleteById(Integer id) {
        Optional<Projeto> optionalProjeto = getProjetoById(id);
        if (optionalProjeto.isPresent()) {
            projetoRepository.deleteById(id);
        } else {
            throw new RuntimeException("Projeto não encontrado");
        }
    }
}
