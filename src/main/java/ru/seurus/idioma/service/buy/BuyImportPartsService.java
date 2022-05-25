package ru.seurus.idioma.service.buy;

import org.springframework.beans.factory.annotation.Autowired;
import ru.seurus.idioma.entity.buy.BuyElectrics;
import ru.seurus.idioma.entity.buy.BuyImportParts;
import ru.seurus.idioma.repository.buy.BuyElectricsRepository;
import ru.seurus.idioma.repository.buy.BuyImportPartsRepository;

import java.util.List;

public class BuyImportPartsService {

    @Autowired
    private BuyImportPartsRepository repository;

    public List<BuyImportParts> findAll() {
        var objects = (List<BuyImportParts>) repository.findAll();
        return objects;
    }
}
