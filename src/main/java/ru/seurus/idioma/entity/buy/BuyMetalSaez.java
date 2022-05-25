package ru.seurus.idioma.entity.buy;

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
@Table(name = "BUY_METAL_SAEZ")
public class BuyMetalSaez {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "COMMENT")
    private String comment;

    @Column(name = "PRICE_EURO")
    private Double priceEuro;

    @Column(name = "LOGISTIC_COEFFICIENCY")
    private Double coef;

    @Column(name = "PRICE_RUBLE")
    private Double priceRuble;
}
