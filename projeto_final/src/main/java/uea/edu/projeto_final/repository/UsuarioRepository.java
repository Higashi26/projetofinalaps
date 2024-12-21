package uea.edu.projeto_final.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import uea.edu.projeto_final.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
