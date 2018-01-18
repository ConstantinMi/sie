package ro.uaic.feaa.common.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Claudiu on 1/8/2018.
 */
public class Indicator {

    private double valoareTrezorerie;
    private double durataRotatie;
    private double rataMarjaSiguranta;
    private double rataFinantareNecesarFondRulment;
    private double cifraAfaceri;

    private Map<String, String> rezultat = new HashMap<>();

    public double getCifraAfaceri() {
        return cifraAfaceri;
    }

    public void setCifraAfaceri(double cifraAfaceri) {
        this.cifraAfaceri = cifraAfaceri;
    }

    public double getValoareTrezorerie() {
        return valoareTrezorerie;
    }

    public void setValoareTrezorerie(double valoareTrezorerie) {
        this.valoareTrezorerie = valoareTrezorerie;
    }

    public double getDurataRotatie() {
        return durataRotatie;
    }

    public void setDurataRotatie(double durataRotatie) {
        this.durataRotatie = durataRotatie;
    }

    public double getRataMarjaSiguranta() {
        return rataMarjaSiguranta;
    }

    public void setRataMarjaSiguranta(double rataMarjaSiguranta) {
        this.rataMarjaSiguranta = rataMarjaSiguranta;
    }

    public double getRataFinantareNecesarFondRulment() {
        return rataFinantareNecesarFondRulment;
    }

    public void setRataFinantareNecesarFondRulment(double rataFinantareNecesarFondRulment) {
        this.rataFinantareNecesarFondRulment = rataFinantareNecesarFondRulment;
    }

    public Map<String, String> getRezultat() {
        return rezultat;
    }

    public void setRezultat(Map<String, String> rezultat) {
        this.rezultat = rezultat;
    }
}
