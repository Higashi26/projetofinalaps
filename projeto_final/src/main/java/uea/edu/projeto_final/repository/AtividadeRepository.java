package uea.edu.projeto_final.repository;

import uea.edu.projeto_final.model.Atividade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AtividadeRepository extends JpaRepository<Atividade, Long> {
    // Métodos de consulta personalizados (se necessário)
    //Exemplos:
    //List<Atividade> findByCategoria(String categoria);
    //List<Atividade> findByDataBetween(LocalDate dataInicio, LocalDate dataFim);
    //List<Atividade> findByCursoId(Long cursoId);
}