package br.com.starwars.entity;

import br.com.starwars.utils.GeneroEnum;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class RebelEntity {
    //nome, idade, gênero, localização (latitude, longitude e nome, na galáxia, da base ao qual faz parte)
    private String nome;
    private Integer idade;
    private GeneroEnum genero;
    private Double latitude; // EX: 1.3248756
    private Double longitude; // EX: 5.72365635
    private String nomeBase; // EX: Tatooine
    private String id;

    private List<InventoryEnum> inventario;


}
