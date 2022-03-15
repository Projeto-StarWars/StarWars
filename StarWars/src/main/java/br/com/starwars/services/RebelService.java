package br.com.starwars.services;

import br.com.starwars.dto.*;
import br.com.starwars.entity.RebelEntity;
import br.com.starwars.repository.RebelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RebelService {

    private final RebelRepository repository;

    public RebelResponseDTO add(RebelRequestDTO request){
        RebelEntity rebel = toEntity(request);
        rebel.setUUIDid(UUID.randomUUID().toString());

        repository.save(rebel);

        RebelResponseDTO responseDTO = toResponseDTO(rebel);

        return responseDTO;
    }

    public List<RebelResponseDTO> getAll(){
        List<RebelEntity> rebels = repository.getAll();

        return rebels.stream()
                .map(entity -> toResponseDTO(entity))
                .collect(Collectors.toList());
    }

    public RebelResponseDTO update(Long id, RebelPacthRequestDTO requestPatch){

        RebelEntity entity = repository.getById(id);
        entity.setLongitude(requestPatch.getLongitude());
        entity.setLatitude(requestPatch.getLatitude());
        entity.setNomeBase(requestPatch.getNomeBase());

        RebelResponseDTO response = toResponseDTO(entity);

        return response;
    }

    public RebelResponseDTO report(Long id){

        RebelEntity entity = repository.getById(id);
        entity.setTraidor(true);
        RebelResponseDTO response = toResponseDTO(entity);

        return response;

    }

    public RelatorioDTO getRelatorio(){

        List<RebelEntity> rebeldes = repository.getAll(); //2
        List<RebelEntity> traidores = repository.sortTraidores(); //1


        double rebeldesSize = rebeldes.size();
        double  traidoresSize = traidores.size();
        double porcentagemTraidores = 0;
        double porcentagemRebeldes = 0;

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

        return relatorio;
    }

    private RebelEntity toEntity(RebelRequestDTO dto){
        RebelEntity entity = new RebelEntity();

        entity.setNome(dto.getNome());
        entity.setIdade(dto.getIdade());
        entity.setGenero(dto.getGenero());

        return entity;
    }

    private RebelResponseDTO toResponseDTO(RebelEntity entity){
        RebelResponseDTO response = new RebelResponseDTO();
        response.setNome(entity.getNome());
        response.setIdade(entity.getIdade());
        response.setGenero(entity.getGenero());
        response.setLatitude(entity.getLatitude());
        response.setLongitude(entity.getLongitude());
        response.setNomeBase(entity.getNomeBase());
        response.setUUIDid(entity.getUUIDid());
        response.setId(entity.getId());
        response.setTraidor(entity.getTraidor());

        return response;
    }
}
