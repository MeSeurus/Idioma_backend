package ru.seurus.idioma.repository.stock;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.seurus.idioma.entity.stock.StockElectrics;
import ru.seurus.idioma.entity.stock.StockMetalSaez;
import ru.seurus.idioma.entity.stock.StockPlasma;

import java.util.List;

public interface StockPlasmaRepository extends JpaRepository<StockPlasma, Integer> {

    @Query(value = "SELECT SUM(quantityCurrent) FROM StockPlasma WHERE recipeId = :recipeId")
    Double getStockQuantity(@Param("recipeId")Integer recipeId);

    @Query(value = "FROM StockPlasma WHERE recipeId = :recipeId ORDER BY date ASC")
    List<StockPlasma> getOldestStock(@Param("recipeId")Integer recipeId);
}
