package ru.seurus.idioma.repository.set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.seurus.idioma.entity.set.Set;

public interface SetRepository extends JpaRepository<Set , Integer> {

        @Query(value = "FROM Set WHERE id = :id")
        Set findPosition(@Param("id")Integer id);
}
