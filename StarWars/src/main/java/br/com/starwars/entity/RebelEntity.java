package br.com.starwars.entity;

import br.com.starwars.utils.GeneroEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class RebelEntity {
    //nome, idade, gênero, localização (latitude, longitude e nome, na galáxia, da base ao qual faz parte)
    private String nome;
    private Integer idade;
    private GeneroEnum genero;
    private Double latitude; // EX: 1.3248756
    private Double longitude; // EX: 5.72365635
    private String nomeBase; // EX: Tatooine
    private String UUIDid;
    private Long id;
    private Boolean traidor;

    private List<InventoryEnum> inventario;

    private Integer reportCount = 0;


}
