package ru.seurus.idioma.service.buy;

import org.springframework.beans.factory.annotation.Autowired;
import ru.seurus.idioma.entity.buy.BuyElectrics;
import ru.seurus.idioma.entity.buy.BuyMetalImport;
import ru.seurus.idioma.repository.buy.BuyElectricsRepository;
import ru.seurus.idioma.repository.buy.BuyMetalImportRepository;

import java.util.List;

public class BuyMetalImportService {

    @Autowired
    private BuyMetalImportRepository repository;

    public List<BuyMetalImport> findAll() {
        var objects = (List<BuyMetalImport>) repository.findAll();
        return objects;
    }
}
