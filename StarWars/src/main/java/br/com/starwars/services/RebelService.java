package br.com.starwars.services;

import br.com.starwars.dto.*;
import br.com.starwars.entity.LocalizationEntity;
import br.com.starwars.entity.RebelEntity;
import br.com.starwars.repository.LocalizationRepository;
import br.com.starwars.repository.RebelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        localizacao.setId(entity.get().getLocalizationEntity().getId());

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

        LocalizationEntity localization = new LocalizationEntity();
        RebelEntity entity = repository.getById(id);
        entity.setLocalizationEntity(localization);
        localization.setId(entity.getLocalizationEntity().getId());
        localization.setLatitude(requestPatch.getLatitude());
        localization.setLongitude(requestPatch.getLongitude());
        localization.setNomeDaBase(requestPatch.getNomeDaBase());
        repository.save(entity);

        return requestPatch;
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
        LocalizationEntity localization = new LocalizationEntity();
        localization.setNomeDaBase(dto.getLocalization().getNomeDaBase());
        localization.setLongitude(dto.getLocalization().getLongitude());
        localization.setLatitude(dto.getLocalization().getLatitude());
        entity.setTraidor(dto.getTraidor());
        entity.setNome(dto.getNome());
        entity.setIdade(dto.getIdade());
        entity.setGenero(dto.getGenero());
        entity.setLocalizationEntity(localization);

        return entity;
    }

    private RebelResponseDTO toResponseDTO(RebelEntity entity) {
        RebelResponseDTO response = new RebelResponseDTO();
        RebelLocalizationResponseDTO localization = new RebelLocalizationResponseDTO();
        localization.setId(entity.getLocalizationEntity().getId());
        localization.setNomeDaBase(entity.getLocalizationEntity().getNomeDaBase());
        localization.setLatitude(entity.getLocalizationEntity().getLatitude());
        localization.setLongitude(entity.getLocalizationEntity().getLongitude());


        response.setNome(entity.getNome());
        response.setIdade(entity.getIdade());
        response.setGenero(entity.getGenero());
        response.setId(entity.getId());
        response.setTraidor(entity.getTraidor());
        response.setLocalizacao(localization);

        return response;
    }
}
