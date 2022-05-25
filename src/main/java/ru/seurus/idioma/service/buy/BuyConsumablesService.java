package ru.seurus.idioma.service.buy;

import org.springframework.beans.factory.annotation.Autowired;
import ru.seurus.idioma.entity.buy.BuyConsumables;
import ru.seurus.idioma.repository.buy.BuyConsumablesRepository;

import java.util.List;

public class BuyConsumablesService {

    @Autowired
    private BuyConsumablesRepository repository;

    public List<BuyConsumables> findAll() {
        var objects = (List<BuyConsumables>) repository.findAll();
        return objects;
    }
}
