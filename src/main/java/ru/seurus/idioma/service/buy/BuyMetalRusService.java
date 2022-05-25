package ru.seurus.idioma.service.buy;

import org.springframework.beans.factory.annotation.Autowired;
import ru.seurus.idioma.entity.buy.BuyElectrics;
import ru.seurus.idioma.entity.buy.BuyMetalRus;
import ru.seurus.idioma.repository.buy.BuyElectricsRepository;
import ru.seurus.idioma.repository.buy.BuyMetalRusRepository;

import java.util.List;

public class BuyMetalRusService {

    @Autowired
    private BuyMetalRusRepository repository;

    public List<BuyMetalRus> findAll() {
        var objects = (List<BuyMetalRus>) repository.findAll();
        return objects;
    }
}
