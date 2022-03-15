package br.com.starwars.services;

import br.com.starwars.dto.RebelRequestDTO;
import br.com.starwars.dto.RebelResponseDTO;
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
        rebel.setId(UUID.randomUUID().toString());

        repository.save(rebel);

        RebelResponseDTO response = toResponseDTO(rebel);

        return response;
    }

    public List<RebelResponseDTO> getAll(){
        List<RebelEntity> rebels = repository.getAll();

        return rebels.stream()
                .map(entity -> toResponseDTO(entity))
                .collect(Collectors.toList());
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
        response.setId(entity.getId());

        return response;
    }
}
