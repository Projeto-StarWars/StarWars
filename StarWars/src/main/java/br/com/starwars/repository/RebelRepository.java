package br.com.starwars.repository;

import br.com.starwars.entity.InventoryEnum;
import br.com.starwars.entity.RebelEntity;
import br.com.starwars.utils.GeneroEnum;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class RebelRepository {
    private static List<RebelEntity> list = new ArrayList<>();
    private static List<RebelEntity> listTraidores = new ArrayList<>();
    private static Long sequence = 1L;
    private static Boolean traidor = false;

    static {
        RebelEntity rebels = new RebelEntity();

        rebels.setNome("Luke");
        rebels.setIdade(20);
        rebels.setGenero(GeneroEnum.MASCULINO);
        rebels.setLatitude(1.348756);
        rebels.setLongitude(7.4678235);
        rebels.setNomeBase("Tatooine");
        rebels.setUUIDid(UUID.randomUUID().toString());
        rebels.setId(sequence++);
        rebels.setTraidor(traidor);
        list.add(rebels);


        RebelEntity rebels2 = new RebelEntity();

        rebels2.setNome("Leia");
        rebels2.setIdade(20);
        rebels2.setGenero(GeneroEnum.FEMININO);
        rebels2.setLatitude(5.4344756);
        rebels2.setLongitude(62.7364);
        rebels2.setNomeBase("Coruscant");
        rebels2.setUUIDid(UUID.randomUUID().toString());
        rebels2.setId(sequence++);
        rebels2.setTraidor(traidor);
        list.add(rebels2);

    }

    public RebelEntity save(RebelEntity rebel){
        rebel.setId(sequence++);
        rebel.setTraidor(traidor);
        list.add(rebel);
        return rebel;
    }

    public List<RebelEntity> getAll(){
        return list;
    }

    public RebelEntity getById(Long id) {
        return list.stream()
                .filter(entity -> entity.getId().equals(id))
                .findFirst()
                .get();
    }

    public void update(RebelEntity entity){
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId().equals(entity.getId())) {
                list.get(i).setLatitude(entity.getLatitude());
                list.get(i).setLongitude(entity.getLongitude());
                list.get(i).setNomeBase(entity.getNomeBase());
                return;
            }
        }
    }

    public List<RebelEntity> sortTraidores(){
        for (int i = 0; i < list.size(); i++){
            if (list.get(i).getTraidor() == true){
                listTraidores.add(list.get(i));
            }
        }
        return listTraidores;
    }



}
