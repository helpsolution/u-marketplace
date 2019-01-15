package com.reljicd.service;

import com.reljicd.exception.NotEnoughProductsInStockException;
import com.reljicd.model.OrderOfSystem;
import com.reljicd.model.Product;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

public interface ShoppingCartService {

    void addProduct(Product product);

    void removeProduct(Product product);

    Map<Product, Integer> getProductsInCart();

//    void checkout(String customerName, String address, String paymentType, Date dateFrom, Date dateTo);
    void checkout(String customerName, String address, OrderOfSystem.PayingType paymentType, Date dateFrom, Date dateTo);

    BigDecimal getTotal();
}
