package ro.uaic.feaa.service.impl;

import org.kie.api.runtime.KieContainer;
import org.kie.internal.KnowledgeBase;
import org.kie.internal.runtime.StatefulKnowledgeSession;
import org.springframework.stereotype.Service;
import ro.uaic.feaa.common.dto.Echilibru;
import ro.uaic.feaa.common.dto.Indicator;
import ro.uaic.feaa.service.IContService;
import ro.uaic.feaa.service.IEchilibruService;
import ro.uaic.feaa.service.IIndicatorService;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * Created by Claudiu on 1/8/2018.
 */
@Service
public class IndicatorServiceImpl implements IIndicatorService {

    private static final String EMPTY = "FARA REZULTAT";

    @Resource
    private IEchilibruService echilibruService;

    @Resource
    private IContService contService;

    @Resource
    private KnowledgeBase knowledgeBase;

    @Override
    public String obtinereDurataRotatie() {
        return situatieIntreprindere(CheieRezultat.DR).orElse(EMPTY);
    }

    @Override
    public String obtinereRMS() {
        return situatieIntreprindere(CheieRezultat.RMS).orElse(EMPTY);
    }

    @Override
    public String obtinereRFNFR() {
        return situatieIntreprindere(CheieRezultat.RFNFR).orElse(EMPTY);
    }

    @Override
    public String obtinereCifraAfaceri() {
        return String.valueOf(contService.calculCifraAfaceri());
    }

    private Optional<String> situatieIntreprindere(CheieRezultat cheieRezultat) {
        Echilibru echilibru = echilibruService.obtinereEchilibru();
        Indicator indicator = new Indicator();
        indicator.setDurataRotatie(calculDurataRotatie(echilibru));
        indicator.setRataMarjaSiguranta(calculRataMarjaSiguranta(echilibru));
        indicator.setRataFinantareNecesarFondRulment(calculRNFNR(echilibru));
        indicator.setValoareTrezorerie(calculTrezorerie(echilibru));

        obtinereRezultatBazatPeReguli(indicator);

        return Optional.ofNullable(indicator.getRezultat().get(cheieRezultat.getKey()));
    }

    private void obtinereRezultatBazatPeReguli(Indicator indicator) {
        StatefulKnowledgeSession ksession = knowledgeBase.newStatefulKnowledgeSession();
        ksession.insert(indicator);
        ksession.fireAllRules();
    }

    private double calculTrezorerie(Echilibru echilibru) {
        double fr = echilibru.getFondRulment();
        double nfr = echilibru.getNecesarFondRulment();

        return fr - nfr;
    }

    private double calculDurataRotatie(Echilibru echilibru) {
        double cifraAfaceri = contService.calculCifraAfaceri();
        double nfr = echilibru.getNecesarFondRulment();

        return nfr / cifraAfaceri * 360;
    }

    private double calculRataMarjaSiguranta(Echilibru echilibru) {
        double cifraAfaceri = contService.calculCifraAfaceri();
        double fr = echilibru.getFondRulment();

        return fr / cifraAfaceri * 360;
    }

    private double calculRNFNR(Echilibru echilibru) {
        double fr = echilibru.getFondRulment();
        double nfr = echilibru.getNecesarFondRulment();

        return fr / nfr * 100;
    }

    private enum CheieRezultat {
        DR("dr"), RMS("rms"), RFNFR("rfnfr");

        CheieRezultat(String key) {
            this.key = key;
        }

        private String key;

        public String getKey() {
            return key;
        }
    }
}
