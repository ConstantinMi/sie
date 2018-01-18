package ro.uaic.feaa.service.impl;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

/**
 * Created by Claudiu on 1/13/2018.
 */
public class DroolsService<T>  {

    public DroolsService(KieContainer container) {
        this.container = container;
    }

    private KieContainer container;

    public void obtinereRezultat(T t) {
        KieSession kieSession = container.newKieSession();
//        kieSession.setGlobal("rideFare", rideFare);
        kieSession.insert(t);
        kieSession.fireAllRules();
        kieSession.dispose();
    }
}
