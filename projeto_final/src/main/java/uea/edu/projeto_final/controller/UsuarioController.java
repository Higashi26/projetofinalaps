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


import uea.edu.projeto_final.dto.NovoCursoDTO;

import uea.edu.projeto_final.service.UsuarioService;
import uea.edu.projeto_final.model.Usuario;


@RestController
@RequestMapping("/Usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService UsuarioService;

    // Listar todos os Usuarios
    @GetMapping
    public ResponseEntity<List<Usuario>> listar() {
        List<Usuario> Usuarios = UsuarioService.listar();
        return ResponseEntity.ok(Usuarios); // Retorna 200 OK
    }

    // Buscar Usuario por ID
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable Long id) {
        return UsuarioService.buscarPorId(id)
                .map(ResponseEntity::ok) // Retorna 200 OK se encontrado
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build()); // Retorna 404 Not Found se não encontrado
    }

    // Criar um novo Usuario
    @PostMapping
    public ResponseEntity<Usuario> criar(@RequestBody Usuario Usuario) {
        Usuario novoUsuario = UsuarioService.salvar(Usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario); // Retorna 201 Created
    }

    // Atualizar um Usuario existente
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizar(@PathVariable Long id, @RequestBody Usuario Usuario) {
        if (!UsuarioService.buscarPorId(id).isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Retorna 404 Not Found se o Usuario não existir
        }
        Usuario.setCodigo(id);
        Usuario UsuarioAtualizado = UsuarioService.salvar(Usuario);
        return ResponseEntity.ok(UsuarioAtualizado); // Retorna 200 OK se atualizado com sucesso
    }

    // Deletar um Usuario por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (!UsuarioService.buscarPorId(id).isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Retorna 404 Not Found se o Usuario não existir
        }
        UsuarioService.deletar(id);
        return ResponseEntity.noContent().build(); // Retorna 204 No Content se deletado com sucesso
    }

    @PostMapping("/novo")
    public ResponseEntity<Usuario> criarUsuario(@RequestBody NovoCursoDTO novoUsuarioDTO) {
        Usuario novoUsuario = new Usuario();
        Usuario UsuarioSalvo = UsuarioService.salvar(novoUsuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(UsuarioSalvo);
    }
}
