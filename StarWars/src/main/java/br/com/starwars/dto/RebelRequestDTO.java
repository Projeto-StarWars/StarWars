package br.com.starwars.dto;

import br.com.starwars.utils.GeneroEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RebelRequestDTO {
    private String nome;
    private Integer idade;
    private GeneroEnum genero;
    private RebelLocalizationRequestDTO localization;
    private Boolean traidor = false;
    private Integer reportCount = 0;
}
