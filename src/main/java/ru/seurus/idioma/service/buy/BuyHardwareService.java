package ru.seurus.idioma.service.buy;

import org.springframework.beans.factory.annotation.Autowired;
import ru.seurus.idioma.entity.buy.BuyElectrics;
import ru.seurus.idioma.entity.buy.BuyHardware;
import ru.seurus.idioma.repository.buy.BuyElectricsRepository;
import ru.seurus.idioma.repository.buy.BuyHardwareRepository;

import java.util.List;

public class BuyHardwareService {

    @Autowired
    private BuyHardwareRepository repository;

    public List<BuyHardware> findAll() {
        var objects = (List<BuyHardware>) repository.findAll();
        return objects;
    }
}
