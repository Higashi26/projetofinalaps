package uea.edu.projeto_final.controller;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import uea.edu.projeto_final.model.Atividade;

import uea.edu.projeto_final.service.AtividadeService;



@RestController
@RequestMapping("/atividadees")
public class AtividadeController {
    @Autowired
    private AtividadeService atividadeService;

    // Listar todos os atividadees
    @GetMapping
    public ResponseEntity<List<Atividade>> listar() {
        List<Atividade> atividadees = atividadeService.listar();
        if (atividadees.isEmpty()) {
            return ResponseEntity.noContent().build(); // Retorna 204 No Content se a lista estiver vazia
        }
        return ResponseEntity.ok(atividadees); // Retorna 200 OK
    }

    // Buscar atividade por ID
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<Atividade> buscarPorId(@PathVariable Long id) {
        return atividadeService.buscarPorId(id)
                .map(ResponseEntity::ok) // Retorna 200 OK se encontrado
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build()); // Retorna 404 Not Found se não encontrado
    }

    // Criar um novo atividade
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Atividade> criar(@RequestBody Atividade atividade) {
        Atividade novoAtividade = atividadeService.salvar(atividade);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoAtividade); // Retorna 201 Created
    }

    // Atualizar um atividade existente
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<Atividade> atualizar(@PathVariable Long id, @RequestBody Atividade atividade) {
        if (!atividadeService.buscarPorId(id).isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Retorna 404 Not Found se o atividade não existir
        }
        atividade.setCodigo(id);
        Atividade atividadeAtualizado = atividadeService.salvar(atividade);
        return ResponseEntity.ok(atividadeAtualizado); // Retorna 200 OK se atualizado com sucesso
    }

    // Deletar um atividade por ID
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (!atividadeService.buscarPorId(id).isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Retorna 404 Not Found se o atividade não existir
        }
        atividadeService.deletar(id);
        return ResponseEntity.noContent().build(); // Retorna 204 No Content se deletado com sucesso
    }

}

