package cat.itacademy.barcelonactiva.CompanyVallet.Ernest.s05.t01.n02.S05T01N02CompanyValletErnest.controller;

import cat.itacademy.barcelonactiva.CompanyVallet.Ernest.s05.t01.n02.S05T01N02CompanyValletErnest.dto.FlorDTO;
import cat.itacademy.barcelonactiva.CompanyVallet.Ernest.s05.t01.n02.S05T01N02CompanyValletErnest.service.FlorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/flor")
public class FlorController {

    @Autowired
    FlorServiceImpl florService;

    @GetMapping("/getOne/{id}")
    public ResponseEntity<FlorDTO> getFlor(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(florService.getFlorById(id), HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<FlorDTO>> getAllFlors(@RequestParam(value = "keyword", required = false) String keyword) {
        List<FlorDTO> list = keyword == null ? florService.getAll() : florService.findByKeyword(keyword);
        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<FlorDTO> addFlor(@Valid @RequestBody FlorDTO florDTO) {
        return new ResponseEntity<>(florService.saveFlor(florDTO), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<FlorDTO> updateFlor(@Valid @RequestBody FlorDTO florDTO, @PathVariable("id") Integer id) {
        return new ResponseEntity<>(florService.updateFlor(id, florDTO), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteFlor(@PathVariable("id") Integer id) {
        florService.deleteFlorById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
