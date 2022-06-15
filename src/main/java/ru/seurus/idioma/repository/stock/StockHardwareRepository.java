package ru.seurus.idioma.repository.stock;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.seurus.idioma.entity.stock.StockElectrics;
import ru.seurus.idioma.entity.stock.StockHardware;
import ru.seurus.idioma.entity.stock.StockMetalSaez;

import java.util.List;

public interface StockHardwareRepository extends JpaRepository<StockHardware, Integer> {

    @Query(value = "SELECT SUM(quantityCurrent) FROM StockHardware WHERE recipeId = :recipeId")
    Double getStockQuantity(@Param("recipeId")Integer recipeId);

    @Query(value = "FROM StockHardware WHERE recipeId = :recipeId ORDER BY date ASC")
    List<StockHardware> getOldestStock(@Param("recipeId")Integer recipeId);
}
