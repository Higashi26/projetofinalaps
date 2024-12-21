package uea.edu.projeto_final.dto;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FotoDTO {
    private Long codigo;
    private String titulo;
    List<String> autores;
}