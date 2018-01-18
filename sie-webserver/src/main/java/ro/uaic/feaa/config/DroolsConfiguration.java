package ro.uaic.feaa.config;

import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieModule;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieContainer;
import org.kie.internal.KnowledgeBase;
import org.kie.internal.KnowledgeBaseFactory;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderError;
import org.kie.internal.builder.KnowledgeBuilderErrors;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.io.ResourceFactory;
import org.kie.internal.runtime.StatefulKnowledgeSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.uaic.feaa.common.dto.Echilibru;
import ro.uaic.feaa.common.dto.Indicator;

/**
 * Created by Claudiu on 1/13/2018.
 */
@Configuration
public class DroolsConfiguration {

    private static final String ECHILIBRU_DRL_FILE = "reguli_echilibru.drl";
    private static final String INDICATOR_DRL_FILE = "reguli_indicator.drl";

    public static void main(String[] args) throws Exception {
        KnowledgeBase kbase = readKnowledgeBase();
        StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();

//        Echilibru echilibru = new Echilibru();
//        echilibru.setFondRulment(123.25);
//        echilibru.setNecesarFondRulment(901.21);
//        echilibru.setCapitalTotal(1231.25);
//
        Indicator echilibru = new Indicator();

        ksession.insert(echilibru);
        ksession.fireAllRules();

        System.out.println(echilibru.getRezultat());


    }
//
    @Bean
    public static KnowledgeBase readKnowledgeBase() throws Exception {

        KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        kbuilder.add(ResourceFactory.newClassPathResource(ECHILIBRU_DRL_FILE), ResourceType.DRL);
        kbuilder.add(ResourceFactory.newClassPathResource(INDICATOR_DRL_FILE), ResourceType.DRL);

        KnowledgeBuilderErrors errors = kbuilder.getErrors();

        if (errors.size() > 0) {
            for (KnowledgeBuilderError error: errors) {
                System.err.println(error);
            }
            throw new IllegalArgumentException("Could not parse knowledge.");
        }

        KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
        kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());

        return kbase;
    }

}
