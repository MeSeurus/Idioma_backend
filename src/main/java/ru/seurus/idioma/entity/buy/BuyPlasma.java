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
@Table(name = "BUY_PLASMA")
public class BuyPlasma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "COMMENT")
    private String comment;

    @Column(name = "PRICE_GOODS")
    private Double priceGoods;

    @Column(name = "PRICE_WORK")
    private Double priceWork;

    @Column(name = "PRICE_RESULT")
    private Double priceResult;
}
