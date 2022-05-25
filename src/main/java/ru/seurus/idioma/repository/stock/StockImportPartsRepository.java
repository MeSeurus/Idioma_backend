package ru.seurus.idioma.repository.stock;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.seurus.idioma.entity.stock.StockElectrics;
import ru.seurus.idioma.entity.stock.StockImportParts;

public interface StockImportPartsRepository extends JpaRepository<StockImportParts, Integer> {
}
