package com.reljicd.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.math.BigDecimal;

/**
 * @author aguminskaya
 * @since 2019-01-14
 */
@Entity(name = "product_in_basket")
@IdClass(ProductInBasketPK.class)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductInBasket {

    @Id
    @Column(name = "product_id")
    private Long productId;

    @Id
    @Column(name = "basket_id")
    private Long basketId;

    @Column(name = "product_count")
    private Long productCount;

    @Column(name = "price_at_now")
    private BigDecimal priceAtNow;
}
