package ru.seurus.idioma.service.buy;

import org.springframework.beans.factory.annotation.Autowired;
import ru.seurus.idioma.entity.buy.BuyElectrics;
import ru.seurus.idioma.entity.buy.BuyPlasma;
import ru.seurus.idioma.repository.buy.BuyElectricsRepository;
import ru.seurus.idioma.repository.buy.BuyPlasmaRepository;

import java.util.List;

public class BuyPlasmaService {

    @Autowired
    private BuyPlasmaRepository repository;

    public List<BuyPlasma> findAll() {
        var objects = (List<BuyPlasma>) repository.findAll();
        return objects;
    }
}
