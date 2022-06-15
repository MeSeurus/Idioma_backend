package ru.seurus.idioma.repository.set;

import org.hibernate.mapping.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.seurus.idioma.entity.set.SetOne;

import java.util.List;

public interface SetOneRepository extends JpaRepository<SetOne, Integer> {

//    @Query(value = "SELECT * FROM set_one", nativeQuery = true)
//    List<SetOne> findAllPositions();

//    @Query(value = "UPDATE :tableName SET quantity = :quantity WHERE name = :name")
//    SetOne updatePositions(@Param("tableName")String tableName,
//                           @Param("quantity")Double quantity,
//                           @Param("recipeId")Integer recipeId,
//                           @Param("name")String name);

}
