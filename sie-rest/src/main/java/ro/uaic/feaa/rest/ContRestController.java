package ro.uaic.feaa.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.uaic.feaa.common.dto.ContDTO;
import ro.uaic.feaa.common.enums.TipCont;
import ro.uaic.feaa.service.IContService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Claudiu on 1/8/2018.
 */
@CrossOrigin
@RestController
@RequestMapping(path = "/cont")
public class ContRestController {

    @Autowired
    private IContService contService;

    @GetMapping(path = "/tipuri")
    public List<String> obtinereTipuriCont() {
        return Stream.of(TipCont.values()).map(TipCont::toString).collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity adaugareContNou(@RequestBody ContDTO contDTO) {
        contService.adaugaCont(contDTO);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity modificareCont(@RequestBody ContDTO contDTO) {
        contService.modificareCont(contDTO);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/toate")
    public ResponseEntity<List<ContDTO>> obtinereConturi() {
        return ResponseEntity.ok(contService.obtinereConturi());
    }

    @GetMapping("/{cod}")
    public ResponseEntity<ContDTO> obtinereContDupaCod(@PathVariable("cod") Long codCont) {
        return ResponseEntity.ok(contService.obtinereContDupaCod(codCont));
    }

    @GetMapping(path = "/hello")
    public ResponseEntity<TipCont> helloWorld() {
        return ResponseEntity.ok(TipCont.ACTIVE_IMOBILIZATE);
    }

}
