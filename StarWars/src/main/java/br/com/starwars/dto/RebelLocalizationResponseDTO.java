package br.com.starwars.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RebelLocalizationResponseDTO {
    private Long id;
    private Double latitude;
    private Double longitude;
    private String nomeDaBase;

}
