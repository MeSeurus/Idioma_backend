package ru.seurus.idioma.entity.set;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "SET_ONE")
public class SetOne {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "QUANTITY")
    private Double quantity;

    @Column(name = "TABLE_NAME")
    private String tableName;

    @Column(name = "RECIPE_ID")
    private Integer recipeId;

}
