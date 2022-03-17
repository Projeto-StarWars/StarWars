package br.com.starwars.controller;

import br.com.starwars.dto.RebelLocalizationRequestDTO;
import br.com.starwars.dto.RebelRequestDTO;
import br.com.starwars.dto.RebelResponseDTO;
import br.com.starwars.dto.RelatorioDTO;
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

    @GetMapping("/relatorios")
    public ResponseEntity<RelatorioDTO> getRelatorio(){
        RelatorioDTO relatorio = service.getRelatorio();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(relatorio);
    }

    @PatchMapping("{id}")
    public ResponseEntity<RebelLocalizationRequestDTO> patchLocation(@PathVariable Long id,
                                                          @RequestBody RebelLocalizationRequestDTO request){

        RebelLocalizationRequestDTO response = service.updateLocalization(id, request);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping("/reportar/{id}")
    public ResponseEntity<RebelResponseDTO> reportRebel(@PathVariable Long id){

        RebelResponseDTO response = service.report(id);


        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }
}
