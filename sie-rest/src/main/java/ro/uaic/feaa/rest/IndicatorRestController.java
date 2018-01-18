package ro.uaic.feaa.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.uaic.feaa.service.IIndicatorService;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Map;

/**
 * Created by Claudiu on 1/8/2018.
 */
@CrossOrigin
@RestController
@RequestMapping(path = "/indicator")
public class IndicatorRestController {

    @Resource
    private IIndicatorService indicatorService;

    @GetMapping("/durata-rotatie")
    public ResponseEntity<Map<String, String>> obtinereDurataRotatie() {
        return ResponseEntity.ok(Collections.singletonMap("valoareDR", indicatorService.obtinereDurataRotatie()));
    }

    @GetMapping("/rms")
    public ResponseEntity<Map<String, String>> obtinereRMS() {
        return ResponseEntity.ok(Collections.singletonMap("valoareRMS", indicatorService.obtinereRMS()));
    }

    @GetMapping("/rfnfr")
    public ResponseEntity<Map<String, String>> obtinereRFNFR() {
        return ResponseEntity.ok(Collections.singletonMap("valoareRFNFR", indicatorService.obtinereRFNFR()));
    }

    @GetMapping("/cifra-afaceri")
    public ResponseEntity<Map<String, String>> obtinereCifraAfaceri() {
        return ResponseEntity.ok(Collections.singletonMap("valoareCA", indicatorService.obtinereCifraAfaceri()));
    }
}
