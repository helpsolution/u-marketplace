package com.reljicd.repository;

import com.reljicd.model.Basket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author aguminskaya
 * @since 2019-01-14
 */
public interface BasketRepository extends JpaRepository<Basket, Long> {

    List<Basket> findAll();

    Basket save(Basket basket);
}
