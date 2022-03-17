package br.com.starwars.services;

import br.com.starwars.dto.*;
import br.com.starwars.entity.InventoryEntity;
import br.com.starwars.entity.InventoryEnum;
import br.com.starwars.entity.LocalizationEntity;
import br.com.starwars.entity.RebelEntity;
import br.com.starwars.repository.LocalizationRepository;
import br.com.starwars.repository.RebelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RebelService {

    @Autowired
    private RebelRepository repository;
    @Autowired
    private LocalizationRepository localizationRepository;

    public RebelResponseDTO add(RebelRequestDTO request) {

        RebelEntity rebel = toEntity(request);
        LocalizationEntity localization = new LocalizationEntity();
        localization.setLatitude(request.getLocalization().getLatitude());
        localization.setLongitude(request.getLocalization().getLongitude());
        localization.setNomeDaBase(request.getLocalization().getNomeDaBase());


        rebel.setLocalizationEntity(localizationRepository.save(localization));


        return toResponseDTO(repository.save(rebel));
    }

    public RebelResponseDTO getById(Long id) {
        Optional<RebelEntity> entity = repository.findById(id);
        RebelResponseDTO responseDTO = new RebelResponseDTO();
        RebelLocalizationResponseDTO localizacao = new RebelLocalizationResponseDTO();

        localizacao.setLatitude(entity.get().getLocalizationEntity().getLatitude());
        localizacao.setLongitude(entity.get().getLocalizationEntity().getLongitude());
        localizacao.setNomeDaBase(entity.get().getLocalizationEntity().getNomeDaBase());


        responseDTO.setGenero(entity.get().getGenero());
        responseDTO.setIdade(entity.get().getIdade());
        responseDTO.setNome(entity.get().getNome());
        responseDTO.setTraidor(entity.get().getTraidor());
        responseDTO.setId(entity.get().getId());
        responseDTO.setLocalizacao(localizacao);

        return responseDTO;
    }


    public List<RebelResponseDTO> getAll() {
        List<RebelEntity> rebels = repository.findAll();

        return rebels.stream()
                .map(entity -> toResponseDTO(entity))
                .collect(Collectors.toList());
    }

    public RebelLocalizationRequestDTO updateLocalization(Long id, RebelLocalizationRequestDTO requestPatch) {

        LocalizationEntity localization = localizationRepository.getById(id);


        localization.setLatitude(requestPatch.getLatitude());
        localization.setLongitude(requestPatch.getLongitude());
        localization.setNomeDaBase(requestPatch.getNomeDaBase());
        localizationRepository.save(localization);

        return requestPatch;
    }

    public RebelResponseDTO report(Long id) {

        RebelEntity entity = repository.getById(id);

        entity.setReportCount(entity.getReportCount() + 1);
        if (entity.getReportCount() >= 3) {
            entity.setTraidor(true);
        }

        repository.save(entity);

        RebelResponseDTO response = toResponseDTO(entity);

        return response;

    }

    public RelatorioDTO getRelatorio() {
        List<RebelEntity> rebeldes = repository.findAll(); //2

        List<RebelEntity> traidores = rebeldes.stream().filter( r -> r.getTraidor().equals(true)).collect(Collectors.toList()); //1

        Double pontosPerdidosTraidores = Double.valueOf(0);
        List<InventoryEnum> armas = new ArrayList<>();
        List<InventoryEnum> municoes = new ArrayList<>();
        List<InventoryEnum> aguas = new ArrayList<>();
        List<InventoryEnum> comidas = new ArrayList<>();

        for (RebelEntity rebelde: rebeldes) {
            System.out.println(1);
            if(rebelde.getTraidor().equals(true)){
                System.out.println(2);
                for (InventoryEntity i: rebelde.getInventario()) {
                    System.out.println(3);
                    if(i.getItem() == InventoryEnum.ARMA){
                        System.out.println(4);
                        pontosPerdidosTraidores = pontosPerdidosTraidores + 4;
                    }
                    if(i.getItem() == InventoryEnum.MUNICAO){
                        pontosPerdidosTraidores = pontosPerdidosTraidores + 3;
                    }
                    if(i.getItem() == InventoryEnum.AGUA){
                        pontosPerdidosTraidores = pontosPerdidosTraidores + 2;
                    }
                    if(i.getItem()  == InventoryEnum.COMIDA){
                        pontosPerdidosTraidores = pontosPerdidosTraidores + 1;
                    }

                }


            }

            for (InventoryEntity i: rebelde.getInventario()) {
                if(i.getItem() == InventoryEnum.ARMA){
                    armas.add(i.getItem());
                }
                if(i.getItem() == InventoryEnum.MUNICAO){
                    municoes.add(i.getItem());
                }
                if(i.getItem() == InventoryEnum.AGUA){
                    aguas.add(i.getItem());
                }
                if(i.getItem() == InventoryEnum.COMIDA){
                    comidas.add(i.getItem());
                }

            }

        }






        double rebeldesSize = rebeldes.size();
        double  traidoresSize = traidores.size();
        double porcentagemTraidores = 0;
        double porcentagemRebeldes = 0;

        double mediaArmas = armas.size() / rebeldesSize;
        double mediaMunicao = municoes.size() / rebeldesSize;
        double mediaAgua = aguas.size() / rebeldesSize;
        double mediaComida = comidas.size() / rebeldesSize;

        if (traidoresSize != 0 && rebeldesSize != 0 && rebeldesSize != traidoresSize){
            porcentagemTraidores = (traidoresSize/rebeldesSize)*100; // (1/2)x100 = 50
            porcentagemRebeldes = 100-porcentagemTraidores; // 100-50
        }else if(traidoresSize == 0){
            porcentagemTraidores = 0;
            porcentagemRebeldes = 100;
        }else if(rebeldesSize == traidoresSize){
            porcentagemTraidores = 100;
            porcentagemRebeldes = 0;
        }

        RelatorioDTO relatorio = new RelatorioDTO();
        relatorio.setPorcentagemTraidores(porcentagemTraidores);
        relatorio.setPorcentagemRebeldes(porcentagemRebeldes);
        relatorio.setPontosPerdidosTraidores(pontosPerdidosTraidores);
        relatorio.setMediaAgua(mediaAgua);
        relatorio.setMediaArmas(mediaArmas);
        relatorio.setMediaComidas(mediaComida);
        relatorio.setMediaMunicao(mediaMunicao);



        return relatorio;
    }

    private RebelEntity toEntity(RebelRequestDTO dto) {
        RebelEntity entity = new RebelEntity();
        LocalizationEntity localization = new LocalizationEntity();
        localization.setNomeDaBase(dto.getLocalization().getNomeDaBase());
        localization.setLongitude(dto.getLocalization().getLongitude());
        localization.setLatitude(dto.getLocalization().getLatitude());
        entity.setTraidor(dto.getTraidor());
        entity.setNome(dto.getNome());
        entity.setIdade(dto.getIdade());
        entity.setGenero(dto.getGenero());
        entity.setLocalizationEntity(localization);

        List<InventoryEntity> inventory = new ArrayList<>();
        dto.getInventoryList().forEach(item -> {
            InventoryEntity inventoryEntity = new InventoryEntity();
            inventoryEntity.setItem(item.getItem());
            inventory.add(inventoryEntity);
        });

        entity.setInventario(inventory);


        return entity;
    }

    private RebelResponseDTO toResponseDTO(RebelEntity entity) {
        RebelResponseDTO response = new RebelResponseDTO();
        RebelLocalizationResponseDTO localization = new RebelLocalizationResponseDTO();
        localization.setNomeDaBase(entity.getLocalizationEntity().getNomeDaBase());
        localization.setLatitude(entity.getLocalizationEntity().getLatitude());
        localization.setLongitude(entity.getLocalizationEntity().getLongitude());

        response.setInventoryList(entity.getInventario());
        response.setNome(entity.getNome());
        response.setIdade(entity.getIdade());
        response.setGenero(entity.getGenero());
        response.setId(entity.getId());
        response.setTraidor(entity.getTraidor());
        response.setLocalizacao(localization);

        return response;
    }
}
