package com.mephi.marketplace.service;

import com.mephi.marketplace.model.*;

public interface BasketService {

    Basket createBasket(Customer customer);

    Basket addProductToBasket(Product product, Customer customer);

    Basket removeProductFromBasket(ProductInBasket productInBasket);


}
