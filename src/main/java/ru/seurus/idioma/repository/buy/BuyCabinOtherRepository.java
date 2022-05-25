package ru.seurus.idioma.repository.buy;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.seurus.idioma.entity.buy.BuyCabinOther;
import ru.seurus.idioma.entity.buy.BuyElectrics;

public interface BuyCabinOtherRepository extends JpaRepository<BuyCabinOther, Integer> {
}
