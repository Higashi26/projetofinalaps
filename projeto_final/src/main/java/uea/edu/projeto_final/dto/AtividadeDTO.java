package uea.edu.projeto_final.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AtividadeDTO {
    private Long codigo;
    private String nome;
    private int quantidadeATividade;
}