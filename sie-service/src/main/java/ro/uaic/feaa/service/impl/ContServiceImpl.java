package ro.uaic.feaa.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ro.uaic.feaa.common.dto.ContDTO;
import ro.uaic.feaa.common.enums.TipCont;
import ro.uaic.feaa.entities.Cont;
import ro.uaic.feaa.repository.IContRepository;
import ro.uaic.feaa.service.IContService;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by Claudiu on 1/8/2018.
 */
@Service
@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
public class ContServiceImpl implements IContService {

    private static final Cont CONT_NULL = new Cont();

    @Resource
    private IContRepository contRepository;

    @Resource
    private ModelMapper modelMapper;

    @PostConstruct
    public void init() {
        CONT_NULL.setDenumire("CONT_INEXISTENT");
    }

    @Override
    public void adaugaCont(ContDTO contDTO) {
        Optional<Cont> contExistent = contRepository.findByCod(contDTO.getCod());
        if (contExistent.isPresent()) {
            modificareCont(contDTO);
        } else {
            contRepository.save(modelMapper.map(contDTO, Cont.class));
        }
    }

    @Override
    public void modificareCont(ContDTO contDTO) {
        contRepository.save(modelMapper.map(contDTO, Cont.class));
    }

    @Override
    public ContDTO obtinereContDupaCod(Long codCont) {
        Cont cont = contRepository.findByCod(codCont).orElse(CONT_NULL);
        return fromModelToDTO(cont);
    }

    @Override
    public List<ContDTO> obtinereConturiDupaTip(String tipCont) {
        return contRepository.findByTipCont(tipCont)
                .stream()
                .map(this::fromModelToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ContDTO> obtinereConturi() {
        return contRepository.obtinereConturi().stream().map(this::fromModelToDTO).collect(Collectors.toList());
    }

    @Override
    public double calculCifraAfaceri() {
        return obtinereConturiDupaTip(TipCont.VENITURI.toString())
                .stream()
                .map(ContDTO::getValoare)
                .reduce(0.0, (a,b) -> a + b);
    }

    private ContDTO fromModelToDTO(Cont cont) {
        return modelMapper.map(cont, ContDTO.class);
    }

}
