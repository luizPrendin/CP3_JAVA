package br.com.fiap.revisaoapi.controller;

import br.com.fiap.revisaoapi.dto.CarroDTO;
import br.com.fiap.revisaoapi.model.Carro;
import br.com.fiap.revisaoapi.service.CarroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/carros", produces = {"application/json"})
@Tag(name = "api-carro")
public class CarroController {

    private final CarroService carroService;

    @Autowired
    public CarroController (CarroService carroService) { this.carroService = carroService; }
    @Operation(summary = "Retorna todos os carros em páginas de 3")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "204", description = "Nenhum carro encontrado", content = {
                    @Content(schema = @Schema())
            })
    })

    @GetMapping
    public ResponseEntity<Page<CarroDTO>> findAll() {
        Page<CarroDTO> carrosDTO = carroService.findAll();
        if (carrosDTO.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            for (CarroDTO carroDTO : carrosDTO){
                Long id = carroDTO.getId();
                carroDTO.add(linkTo(methodOn(CarroController.class)
                        .findById(id)).withSelfRel());
            }
        }
        return ResponseEntity.ok(carrosDTO);
    }

    @Operation(summary = "Retorna um carro específico por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "204", description = "Nenhum carro encontrado para o id informado", content = {
                    @Content(schema = @Schema())
            })
    })
    @GetMapping("/{id}")
    public ResponseEntity<CarroDTO> findById(@PathVariable Long id) {
        CarroDTO carroDTO = carroService.findById(id);
        if (carroDTO == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            carroDTO.add(linkTo(methodOn(CarroController.class)
                    .findAll()).withRel("Lista de Carros"));
        }
        return ResponseEntity.ok(carroDTO);
    }

    @Operation(summary = "Grava um carro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Carro gravado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de validação dos dados", content = {
                    @Content(schema = @Schema())
            })
    })
    @PostMapping
    public ResponseEntity<Carro> save(@Valid @RequestBody Carro carro) {
        Carro carroSalvo = carroService.save(carro);
        return new ResponseEntity<>(carroSalvo, HttpStatus.CREATED);
    }

    @Operation(summary = "Atualiza um carro com base no id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Carro atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de validação dos dados", content = {
                    @Content(schema = @Schema())
            })
    })
    @PutMapping("/{id}")
    public ResponseEntity<Carro> update(@PathVariable Long id, @Valid @RequestBody Carro carro) {
        Carro carroSalvo = carroService.update(id, carro);
        return new ResponseEntity<>(carroSalvo, HttpStatus.CREATED);
    }

    @Operation(summary = "Exclui um carro com base no id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Carro excluído com sucesso", content = {
                    @Content(schema = @Schema())
            })
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        carroService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
