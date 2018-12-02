package com.mephi.marketplace.service;

import com.mephi.marketplace.model.Basket;
import com.mephi.marketplace.model.OrderOfSystem;
import com.mephi.marketplace.model.PayingType;


import java.time.LocalDateTime;

/**
 * @author Anna Guminskaya
 * @since 19/11/2018.
 */
public interface OrderService {

    OrderOfSystem createOrderFromBasket(Basket basket, String deliveryAddress, LocalDateTime deliveryTime,
                                        PayingType payingType, LocalDateTime payingTime);


}
