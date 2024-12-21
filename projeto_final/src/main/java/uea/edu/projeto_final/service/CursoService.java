package uea.edu.projeto_final.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uea.edu.projeto_final.model.Curso;
import uea.edu.projeto_final.repository.CursoRepository;

@Service
public class CursoService {
    @Autowired
    private CursoRepository cursoRepository;

    public List<Curso> listar() {
        return cursoRepository.findAll();
    }

    public Optional<Curso> buscarPorId(Long id) {
        return cursoRepository.findById(id);
    }

    public Curso salvar(Curso curso) {
        return cursoRepository.save(curso);
    }

    public void deletar(Long id) {
        cursoRepository.deleteById(id);
    }
}
