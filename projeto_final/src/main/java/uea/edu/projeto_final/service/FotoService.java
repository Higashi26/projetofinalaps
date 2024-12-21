package uea.edu.projeto_final.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uea.edu.projeto_final.model.Foto;
import uea.edu.projeto_final.repository.FotoRepository;

@Service
public class FotoService {
    @Autowired
    private FotoRepository fotoRepository;

    public List<Foto> listar() {
        return fotoRepository.findAll();
    }

    public Optional<Foto> buscarPorId(Long id) {
        return fotoRepository.findById(id);
    }

    public Foto salvar(Foto foto) {
        return fotoRepository.save(foto);
    }

    public void deletar(Long id) {
        fotoRepository.deleteById(id);
    }
}
