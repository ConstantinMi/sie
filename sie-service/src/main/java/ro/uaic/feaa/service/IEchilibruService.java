package ro.uaic.feaa.service;

import ro.uaic.feaa.common.dto.Echilibru;

/**
 * Created by Claudiu on 1/8/2018.
 */
public interface IEchilibruService {

    Echilibru obtinereEchilibru();

    String obtinereRezultateFondRulment();

    String obtinereRezultateNecesarFondRulment();

    String obtinereRezultateTrezorerie();
}
