package br.com.starwars.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class LocalizationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Double latitude; // EX: 1.3248756
    private Double longitude; // EX: 5.72365635
    private String nomeDaBase; // EX: Tatooine
}
