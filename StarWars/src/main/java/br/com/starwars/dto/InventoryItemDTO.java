package br.com.starwars.dto;

import br.com.starwars.entity.InventoryEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InventoryItemDTO {
    private InventoryEnum item;
}
