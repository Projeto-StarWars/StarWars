package br.com.starwars.entity;

import br.com.starwars.utils.GeneroEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
public class RebelEntity {
    //nome, idade, gênero, localização (latitude, longitude e nome, na galáxia, da base ao qual faz parte)

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Integer idade;
    private GeneroEnum genero;
    private Boolean traidor;
    @OneToOne
    private LocalizationEntity localizationEntity;

    //private List<InventoryEnum> inventario;

    private Integer reportCount = 0;


}
