package ru.seurus.idioma.repository.stock;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.seurus.idioma.entity.stock.StockElectrics;
import ru.seurus.idioma.entity.stock.StockMetalSaez;

import java.util.List;

public interface StockElectricsRepository extends JpaRepository<StockElectrics, Integer> {

    @Query(value = "SELECT SUM(quantityCurrent) FROM StockElectrics WHERE recipeId = :recipeId")
    Double getStockQuantity(@Param("recipeId")Integer recipeId);

    @Query(value = "FROM StockElectrics WHERE recipeId = :recipeId ORDER BY date ASC")
    List<StockElectrics> getOldestStock(@Param("recipeId")Integer recipeId);
}
