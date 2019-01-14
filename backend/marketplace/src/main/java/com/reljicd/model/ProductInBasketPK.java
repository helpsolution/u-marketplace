package com.reljicd.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author aguminskaya
 * @since 2019-01-14
 */
@Data
public class ProductInBasketPK implements Serializable {
    private Long productId;
    private Long basketId;
}
