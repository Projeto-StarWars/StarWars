package br.com.starwars.controller;

import br.com.starwars.dto.RebelPacthRequestDTO;
import br.com.starwars.dto.RebelReportDTO;
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

        List<RebelResponseDTO> rebels = service.getAll();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(rebels);
    }

    @PatchMapping("{id}")
    public ResponseEntity<RebelResponseDTO> patchLocation(@PathVariable Long id,
                                                          @RequestBody RebelPacthRequestDTO request){

        RebelResponseDTO response = service.update(id, request);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @PatchMapping("/reportar/{id}")
    public ResponseEntity<RebelResponseDTO> reportRebel(@PathVariable Long id){

        RebelResponseDTO response = service.report(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }
}
