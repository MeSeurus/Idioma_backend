package ru.seurus.idioma.repository.stock;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.seurus.idioma.entity.stock.StockMetalSaez;

import java.util.List;

@Repository
public interface StockMetalSaezRepository extends JpaRepository<StockMetalSaez, Integer> {

    @Query(value = "SELECT SUM(quantityCurrent) FROM StockMetalSaez WHERE recipeId = :recipeId")
    Double getStockQuantity(@Param("recipeId")Integer recipeId);

    @Query(value = "FROM StockMetalSaez WHERE recipeId = :recipeId ORDER BY date ASC")
    List<StockMetalSaez> getOldestStock(@Param("recipeId")Integer recipeId);


}
