package ru.seurus.idioma.repository.buy;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.seurus.idioma.entity.buy.BuyConsumables;
import ru.seurus.idioma.entity.buy.BuyElectrics;

public interface BuyConsumablesRepository extends JpaRepository<BuyConsumables, Integer> {
}
