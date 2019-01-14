package com.reljicd.repository;

import com.reljicd.model.Basket;
import com.reljicd.model.ProductInBasket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;


public interface ProductInBasketRepository extends JpaRepository<ProductInBasket, Long> {

    List<ProductInBasket> findAll();

    Collection<ProductInBasket> findProductInBasketByBasketId(@Param("id") Long id);
}
