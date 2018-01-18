package ro.uaic.feaa.service.impl;

import org.kie.internal.KnowledgeBase;
import org.kie.internal.runtime.StatefulKnowledgeSession;
import org.springframework.stereotype.Service;
import ro.uaic.feaa.common.dto.ContDTO;
import ro.uaic.feaa.common.dto.Echilibru;
import ro.uaic.feaa.common.enums.TipCont;
import ro.uaic.feaa.service.IContService;
import ro.uaic.feaa.service.IEchilibruService;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * Created by Claudiu on 1/8/2018.
 */
@Service
public class EchilibruServiceImpl implements IEchilibruService {

    private static final String EMPTY = "FARA REZULTAT";

    @Resource
    private IContService contService;

    @Resource
    private KnowledgeBase knowledgeBase;

    @Override
    public String obtinereRezultateFondRulment() {
        return obtinereRezultatBazatPeReguli(CheieRezultat.FR).orElse(EMPTY);
    }

    @Override
    public String obtinereRezultateNecesarFondRulment() {
        return obtinereRezultatBazatPeReguli(CheieRezultat.NFR).orElse(EMPTY);
    }

    @Override
    public String obtinereRezultateTrezorerie() {
        return obtinereRezultatBazatPeReguli(CheieRezultat.TRZ).orElse(EMPTY);
    }

    private Optional<String> obtinereRezultatBazatPeReguli(CheieRezultat cheieRezultat) {
        Echilibru echilibru = obtinereEchilibru();
        StatefulKnowledgeSession ksession = knowledgeBase.newStatefulKnowledgeSession();
        ksession.insert(echilibru);
        ksession.fireAllRules();

        return Optional.ofNullable(echilibru.getRezultat().get(cheieRezultat.getKey()));
    }

    @Override
    public Echilibru obtinereEchilibru() {
        Echilibru echilibru = new Echilibru();
        echilibru.setFondRulment(calculValoareEchilibru(
                TipCont.CAPITAL_PROPRIU.toString(),
                TipCont.DATORII_TERMEN_LUNG.toString(),
                TipCont.ACTIVE_IMOBILIZATE.toString()));

        echilibru.setNecesarFondRulment(calculValoareEchilibru(
                TipCont.STOCURI.toString(),
                TipCont.CREANTE_TOTALE.toString(),
                TipCont.DATORII_RESURSE_CURENTE.toString()));

        return echilibru;
    }

    private double calculValoareEchilibru(String tip1, String tip2, String tip3) {
        double sumaTip1 = totalValoareConturiDupaTip(tip1);
        double sumaTip2 = totalValoareConturiDupaTip(tip2);
        double sumaTip3 = totalValoareConturiDupaTip(tip3);

        return sumaTip1 + sumaTip2 - sumaTip3;
    }

    private double totalValoareConturiDupaTip(String tipCont) {
        return contService.obtinereConturiDupaTip(tipCont)
                .stream()
                .map(ContDTO::getValoare)
                .reduce(0.0, (a, b) -> a + b);
    }

    private enum CheieRezultat {
        FR("fr"), NFR("nfr"), TRZ("trz");

        CheieRezultat(String key) {
            this.key = key;
        }

        private String key;

        public String getKey() {
            return key;
        }
    }
}
