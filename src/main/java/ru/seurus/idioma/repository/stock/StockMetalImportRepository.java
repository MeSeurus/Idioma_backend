package ru.seurus.idioma.repository.stock;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.seurus.idioma.entity.stock.StockElectrics;
import ru.seurus.idioma.entity.stock.StockMetalImport;
import ru.seurus.idioma.entity.stock.StockMetalSaez;

import java.util.List;

public interface StockMetalImportRepository extends JpaRepository<StockMetalImport, Integer> {

    @Query(value = "SELECT SUM(quantityCurrent) FROM StockMetalImport WHERE recipeId = :recipeId")
    Double getStockQuantity(@Param("recipeId")Integer recipeId);

    @Query(value = "FROM StockMetalImport WHERE recipeId = :recipeId ORDER BY date ASC")
    List<StockMetalImport> getOldestStock(@Param("recipeId")Integer recipeId);
}
