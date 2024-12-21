package uea.edu.projeto_final.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CursoDTO {
    private Long codigo;
    private String titulo;
    private String categoria;
}
