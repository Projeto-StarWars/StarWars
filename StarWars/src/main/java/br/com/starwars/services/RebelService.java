package br.com.starwars.services;

import br.com.starwars.dto.*;
import br.com.starwars.entity.LocalizationEntity;
import br.com.starwars.entity.RebelEntity;
import br.com.starwars.repository.RebelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RebelService {

    @Autowired
    private RebelRepository repository;

    public RebelResponseDTO add(RebelRequestDTO request) {
        RebelEntity rebel = toEntity(request);
        repository.save(rebel);
        RebelResponseDTO responseDTO = toResponseDTO(rebel);

        return responseDTO;
    }

    public RebelResponseDTO getById(Long id) {
        RebelResponseDTO responseDTO = repository.findById(id);
        return
    }


    public List<RebelResponseDTO> getAll() {
        List<RebelEntity> rebels = repository.findAll();

        return rebels.stream()
                .map(entity -> toResponseDTO(entity))
                .collect(Collectors.toList());
    }

    public RebelResponseDTO update(Long id, RebelLocalizationRequestDTO requestPatch) {


        LocalizationEntity localization = new LocalizationEntity();
        localization.setId(1L);
      /*  localization.setLatitude(requestPatch.getLatitude());
        localization.setLongitude(requestPatch.getLongitude());
        localization.setNomeBase(requestPatch.getNomeBase());*/

        RebelEntity entity = repository.getById(id);
        entity.setLocalizationEntity(localization);


        RebelResponseDTO response = toResponseDTO(entity);

        return response;
    }

    public RebelResponseDTO report(Long id) {

        RebelEntity entity = repository.getById(id);
        entity.setTraidor(true);
        RebelResponseDTO response = toResponseDTO(entity);

        return response;

    }

    public RelatorioDTO getRelatorio() {
        double rebeldesSize = 0;
        double traidoresSize = 0;

        List<RebelEntity> rebeldes = repository.findAll();




/*
        double rebeldesSize = rebeldes.size();
        double  traidoresSize = traidores.size();
        double porcentagemTraidores = 0;
        double porcentagemRebeldes = 0;*/

       /* if (traidoresSize != 0 && rebeldesSize != 0 && rebeldesSize != traidoresSize){
            porcentagemTraidores = (traidoresSize/rebeldesSize)*100; // (1/2)x100 = 50
            porcentagemRebeldes = 100-porcentagemTraidores; // 100-50
        }else if(traidoresSize == 0){
            porcentagemTraidores = 0;
            porcentagemRebeldes = 100;
        }else if(rebeldesSize == traidoresSize){
            porcentagemTraidores = 100;
            porcentagemRebeldes = 0;
        }*/

        RelatorioDTO relatorio = new RelatorioDTO();
   /*     relatorio.setPorcentagemTraidores(porcentagemTraidores);
        relatorio.setPorcentagemRebeldes(porcentagemRebeldes);*/

        return relatorio;
    }

    private RebelEntity toEntity(RebelRequestDTO dto) {
        RebelEntity entity = new RebelEntity();

        entity.setNome(dto.getNome());
        entity.setIdade(dto.getIdade());
        entity.setGenero(dto.getGenero());

        return entity;
    }

    private RebelResponseDTO toResponseDTO(RebelEntity entity) {
        RebelResponseDTO response = new RebelResponseDTO();
        response.setNome(entity.getNome());
        response.setIdade(entity.getIdade());
        response.setGenero(entity.getGenero());
        response.setId(entity.getId());
        response.setTraidor(entity.getTraidor());

        return response;
    }
}
