package br.com.starwars.controller;

import br.com.starwars.dto.RebelRequestDTO;
import br.com.starwars.dto.RebelResponseDTO;
import br.com.starwars.entity.RebelEntity;
import br.com.starwars.repository.RebelRepository;
import br.com.starwars.services.RebelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/rebeldes")
@RequiredArgsConstructor
public class RebelController {

    private final RebelService service;

    @PostMapping
    public ResponseEntity<RebelResponseDTO> addRebel(@RequestBody RebelRequestDTO request){
        RebelResponseDTO responseDTO = service.add(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(responseDTO);
    }

    @GetMapping
    public ResponseEntity<List<RebelResponseDTO>> getAll(){

        //List<RebelEntity> rebeldes = service.getAll();

        //return ResponseEntity.ok(rebeldes);

        List<RebelResponseDTO> rebels = service.getAll();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(rebels);
    }
}
