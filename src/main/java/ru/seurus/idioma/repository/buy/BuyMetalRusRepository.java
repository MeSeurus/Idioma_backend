package ru.seurus.idioma.repository.buy;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.seurus.idioma.entity.buy.BuyElectrics;
import ru.seurus.idioma.entity.buy.BuyMetalRus;

public interface BuyMetalRusRepository extends JpaRepository<BuyMetalRus, Integer> {
}
