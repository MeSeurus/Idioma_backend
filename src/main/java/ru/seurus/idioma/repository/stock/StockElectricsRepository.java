package ru.seurus.idioma.repository.stock;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.seurus.idioma.entity.stock.StockElectrics;

public interface StockElectricsRepository extends JpaRepository<StockElectrics, Integer> {
}
