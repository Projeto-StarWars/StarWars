package br.com.starwars.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RelatorioDTO {
    private double porcentagemTraidores;
    private double porcentagemRebeldes;
    private double mediaArmas;
    private double mediaMunicao;
    private double mediaComidas;
    private double mediaAgua;
    private double pontosPerdidosTraidores;
}
