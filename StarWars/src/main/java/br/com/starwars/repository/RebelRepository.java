package br.com.starwars.repository;

import br.com.starwars.entity.RebelEntity;
import br.com.starwars.utils.GeneroEnum;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class RebelRepository {
    private static List<RebelEntity> list = new ArrayList<>();

    static {
        RebelEntity rebels = new RebelEntity();

        rebels.setNome("Luke");
        rebels.setIdade(20);
        rebels.setGenero(GeneroEnum.MASCULINO);
        rebels.setLatitude(1.348756);
        rebels.setLongitude(7.4678235);
        rebels.setNomeBase("Tatooine");
        rebels.setId(UUID.randomUUID().toString());
        list.add(rebels);


        RebelEntity rebels2 = new RebelEntity();

        rebels2.setNome("Leia");
        rebels2.setIdade(20);
        rebels2.setGenero(GeneroEnum.FEMININO);
        rebels2.setLatitude(5.4344756);
        rebels2.setLongitude(62.7364);
        rebels2.setNomeBase("Coruscant");
        rebels2.setId(UUID.randomUUID().toString());
        list.add(rebels2);

    }

    public RebelEntity save(RebelEntity rebel){
        list.add(rebel);
        return rebel;
    }

    public List<RebelEntity> getAll(){
        return list;
    }

}
