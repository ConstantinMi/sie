package ro.uaic.feaa.service;

import ro.uaic.feaa.common.dto.ContDTO;

import java.util.List;

/**
 * Created by Claudiu on 1/8/2018.
 */
public interface IContService {

    void adaugaCont(ContDTO contDTO);

    void modificareCont(ContDTO contDTO);

    ContDTO obtinereContDupaCod(Long codCont);

    List<ContDTO> obtinereConturiDupaTip(String tipCont);

    List<ContDTO> obtinereConturi();

    double calculCifraAfaceri();

}
