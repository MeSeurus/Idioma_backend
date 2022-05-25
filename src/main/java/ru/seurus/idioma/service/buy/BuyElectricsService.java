package ru.seurus.idioma.service.buy;

import org.springframework.beans.factory.annotation.Autowired;
import ru.seurus.idioma.entity.buy.BuyElectrics;
import ru.seurus.idioma.repository.buy.BuyElectricsRepository;

import java.util.List;

public class BuyElectricsService {

    @Autowired
    private BuyElectricsRepository repository;

    public List<BuyElectrics> findAll() {
        var objects = (List<BuyElectrics>) repository.findAll();
        return objects;
    }

}
