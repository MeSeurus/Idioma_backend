package ru.seurus.idioma.repository.set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.seurus.idioma.entity.set.SetPositions;

import java.util.List;

public interface SetPositionsRepository extends JpaRepository<SetPositions, Integer> {

    @Query(value = "SELECT id FROM SetPositions WHERE setId = :setId")
    List<Integer> forDelete(@Param("setId")Integer setId);

    @Query(value = "FROM SetPositions WHERE setId = :setId")
    List<SetPositions> forCheck(@Param("setId")Integer setId);

}
