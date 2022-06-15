package ru.seurus.idioma.repository.stock;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.seurus.idioma.entity.stock.StockConsumables;
import ru.seurus.idioma.entity.stock.StockElectrics;
import ru.seurus.idioma.entity.stock.StockMetalSaez;

import java.util.List;

public interface StockConsumablesRepository extends JpaRepository<StockConsumables, Integer> {

    @Query(value = "SELECT SUM(quantityCurrent) FROM StockConsumables WHERE recipeId = :recipeId")
    Double getStockQuantity(@Param("recipeId")Integer recipeId);

    @Query(value = "FROM StockConsumables WHERE recipeId = :recipeId ORDER BY date ASC")
    List<StockConsumables> getOldestStock(@Param("recipeId")Integer recipeId);
}
