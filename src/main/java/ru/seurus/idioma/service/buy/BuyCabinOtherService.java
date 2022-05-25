package ru.seurus.idioma.service.buy;

import org.springframework.beans.factory.annotation.Autowired;
import ru.seurus.idioma.entity.buy.BuyCabinOther;
import ru.seurus.idioma.entity.buy.BuyElectrics;
import ru.seurus.idioma.repository.buy.BuyCabinOtherRepository;
import ru.seurus.idioma.repository.buy.BuyElectricsRepository;

import java.util.List;

public class BuyCabinOtherService {

    @Autowired
    private BuyCabinOtherRepository repository;

    public List<BuyCabinOther> findAll() {
        var objects = (List<BuyCabinOther>) repository.findAll();
        return objects;
    }
}
