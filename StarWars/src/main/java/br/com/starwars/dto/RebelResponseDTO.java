package br.com.starwars.dto;

import br.com.starwars.utils.GeneroEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RebelResponseDTO {
    private String nome;
    private Integer idade;
    private GeneroEnum genero;
    private Double latitude;
    private Double longitude;
    private String nomeBase;
    private String id;
}
