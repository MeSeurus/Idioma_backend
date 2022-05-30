package ru.seurus.idioma.entity.leftover;

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
@Table(name = "LEFTOVERS")
public class Leftovers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "RECIPE_ID")
    private Integer recipeId;

    @Column(name = "NAME")
    private String name;

    @Column(name = "COMMENT")
    private String comment;

    @Column(name = "QUANTITY")
    private Double quantity;

    @Column(name = "PURCHASE_DATE")
    private String date;

}
