package ru.seurus.idioma.repository.stock;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.seurus.idioma.entity.stock.StockElectrics;
import ru.seurus.idioma.entity.stock.StockPlasma;

public interface StockPlasmaRepository extends JpaRepository<StockPlasma, Integer> {
}
