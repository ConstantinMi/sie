package ro.uaic.feaa.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.uaic.feaa.service.IEchilibruService;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Map;

/**
 * Created by Claudiu on 1/8/2018.
 */
@CrossOrigin
@RestController
@RequestMapping("/echilibru")
public class EchilibruRestController {

    @Resource
    private IEchilibruService echilibruService;

    @GetMapping("/fond-rulment")
    public ResponseEntity<Map<String, String>> situatieFondRulment() {
        return ResponseEntity.ok(Collections.singletonMap("valoareFR", echilibruService.obtinereRezultateFondRulment()));
    }

    @GetMapping("/necesar-fond-rulment")
    public ResponseEntity<Map<String, String>> situatieNecesarFondRulment() {
        return ResponseEntity.ok(Collections.singletonMap("valoareNFR", echilibruService.obtinereRezultateNecesarFondRulment()));
    }

    @GetMapping("/trezorerie")
    public ResponseEntity<Map<String, String>> situatieTrezorerie() {
        return ResponseEntity.ok(Collections.singletonMap("valoareTRZ", echilibruService.obtinereRezultateTrezorerie()));
    }
}
