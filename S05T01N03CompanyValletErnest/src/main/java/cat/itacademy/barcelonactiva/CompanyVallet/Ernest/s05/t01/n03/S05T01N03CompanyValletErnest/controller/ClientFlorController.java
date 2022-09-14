package cat.itacademy.barcelonactiva.CompanyVallet.Ernest.s05.t01.n03.S05T01N03CompanyValletErnest.controller;

import cat.itacademy.barcelonactiva.CompanyVallet.Ernest.s05.t01.n03.S05T01N03CompanyValletErnest.dto.FlorDTO;
import cat.itacademy.barcelonactiva.CompanyVallet.Ernest.s05.t01.n03.S05T01N03CompanyValletErnest.service.ClientFlorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("/clientFlors")
public class ClientFlorController {

    @Autowired
    ClientFlorServiceImpl florService;

    @GetMapping("/all")
    public ResponseEntity<Flux<FlorDTO>> getAllFlors(@RequestParam(value = "keyword", required = false) String keyword) {
        if (keyword == null) {
            return new ResponseEntity<>(florService.getAll(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(florService.findByKeyword(keyword), HttpStatus.OK);
        }
    }

    @GetMapping("/getOne/{id}")
    public ResponseEntity<Mono<FlorDTO>> getOneFlor(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(florService.getFlorById(id), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Mono<Void>> addFlor(@Valid @RequestBody FlorDTO florDTO) {
        return new ResponseEntity<>(florService.saveFlor(florDTO), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Mono<FlorDTO>> updateFlor(@Valid @RequestBody FlorDTO florDTO, @PathVariable("id") Integer id) {
        return new ResponseEntity<>(florService.updateFlor(id, florDTO), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Mono<Void>> deleteFlor(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(florService.deleteFlorById(id), HttpStatus.NO_CONTENT);
    }
}
