package br.com.starwars.dto;

import br.com.starwars.entity.InventoryEntity;
import br.com.starwars.utils.GeneroEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RebelResponseDTO {
    private String nome;
    private Integer idade;
    private GeneroEnum genero;
    private RebelLocalizationResponseDTO localizacao;
    private Long id;
    private Boolean traidor;
    private List<InventoryEntity> inventoryList;
}
