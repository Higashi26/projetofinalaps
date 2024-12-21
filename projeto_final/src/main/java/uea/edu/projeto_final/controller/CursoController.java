package uea.edu.projeto_final.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import uea.edu.projeto_final.model.Curso;
import uea.edu.projeto_final.service.CursoService;

@RestController
@RequestMapping("/cursos")
public class CursoController {
    @Autowired
    private CursoService cursoService;

    // Listar todas as cursos
    @GetMapping
    public ResponseEntity<List<Curso>> listar() {
        List<Curso> cursos = cursoService.listar();
        return ResponseEntity.ok(cursos); // Retorna 200 OK
    }

    // Buscar curso por ID
    @GetMapping("/{id}")
    public ResponseEntity<Curso> buscarPorId(@PathVariable Long id) {
        return cursoService.buscarPorId(id)
                .map(ResponseEntity::ok) // Retorna 200 OK se encontrado
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build()); // Retorna 404 Not Found se não encontrado
    }

    // Criar uma nova curso
    @PostMapping
    public ResponseEntity<Curso> criar(@RequestBody Curso curso) {
        Curso novaCurso = cursoService.salvar(curso);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaCurso); // Retorna 201 Created
    }

    // Atualizar uma curso existente
    @PutMapping("/{id}")
    public ResponseEntity<Curso> atualizar(@PathVariable Long id, @RequestBody Curso curso) {
        if (!cursoService.buscarPorId(id).isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Retorna 404 Not Found se a curso não
                                                                        // existir
        }
        curso.setCodigo(id);
        Curso cursoAtualizada = cursoService.salvar(curso);
        return ResponseEntity.ok(cursoAtualizada); // Retorna 200 OK se atualizado com sucesso
    }

    // Deletar uma curso por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (!cursoService.buscarPorId(id).isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Retorna 404 Not Found se a curso não
                                                                        // existir
        }
        cursoService.deletar(id);
        return ResponseEntity.noContent().build(); // Retorna 204 No Content se deletado com sucesso
    }
}


