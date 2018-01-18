package ro.uaic.feaa.common.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Claudiu on 1/8/2018.
 */
public class Echilibru {

    private double necesarFondRulment;
    private double fondRulment;
    private double capitalTotal;

    private Map<String, String> rezultat = new HashMap<>();

    public Map<String, String> getRezultat() {
        return rezultat;
    }

    public void setRezultat(Map<String, String> rezultat) {
        this.rezultat = rezultat;
    }

    public double getNecesarFondRulment() {
        return necesarFondRulment;
    }

    public void setNecesarFondRulment(double necesarFondRulment) {
        this.necesarFondRulment = necesarFondRulment;
    }

    public double getFondRulment() {
        return fondRulment;
    }

    public void setFondRulment(double fondRulment) {
        this.fondRulment = fondRulment;
    }

    public double getCapitalTotal() {
        return capitalTotal;
    }

    public void setCapitalTotal(double capitalTotal) {
        this.capitalTotal = capitalTotal;
    }
}
