package ru.seurus.idioma.service.buy;

import org.springframework.beans.factory.annotation.Autowired;
import ru.seurus.idioma.entity.buy.BuyElectrics;
import ru.seurus.idioma.entity.buy.BuyMetalSaez;
import ru.seurus.idioma.repository.buy.BuyElectricsRepository;
import ru.seurus.idioma.repository.buy.BuyMetalSaezRepository;

import java.util.List;

public class BuyMetalSaezService {

    @Autowired
    private BuyMetalSaezRepository repository;

    public List<BuyMetalSaez> findAll() {
        var objects = (List<BuyMetalSaez>) repository.findAll();
        return objects;
    }
}
