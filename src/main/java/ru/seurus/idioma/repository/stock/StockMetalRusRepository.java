package ru.seurus.idioma.repository.stock;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.seurus.idioma.entity.stock.StockMetalRus;
import ru.seurus.idioma.entity.stock.StockMetalSaez;

import java.util.List;

public interface StockMetalRusRepository extends JpaRepository<StockMetalRus, Integer> {

    @Query(value = "SELECT SUM(quantityCurrent) FROM StockMetalRus WHERE recipeId = :recipeId")
    Double getStockQuantity(@Param("recipeId")Integer recipeId);

    @Query(value = "FROM StockMetalRus WHERE recipeId = :recipeId ORDER BY date ASC")
    List<StockMetalRus> getOldestStock(@Param("recipeId")Integer recipeId);

}
