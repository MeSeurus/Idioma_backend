package ru.seurus.idioma.entity.stock;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "STOCK_METAL_SAEZ")
public class StockMetalSaez implements MethodsStock{

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

    @Column(name = "PURCHASE_DATE")
    private LocalDate date;

    @Column(name = "LOG_COEFFICIENCY")
    private Double coef;

    @Column(name = "QUANTITY_BOUGHT")
    private Double quantityBought;

    @Column(name = "QUANTITY_CURRENT")
    private Double quantityCurrent;

    @Column(name = "PRICE_PU_EURO")
    private Double pricePUEuro;

    @Column(name = "PRICE_PU")
    private Double pricePU;

    @Column(name = "PRICE_RESULT")
    private Double priceResult;
}
