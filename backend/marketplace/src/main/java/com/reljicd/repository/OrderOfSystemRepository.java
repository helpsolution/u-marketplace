package com.reljicd.repository;

import com.reljicd.model.OrderOfSystem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author aguminskaya
 * @since 2019-01-14
 */
public interface OrderOfSystemRepository extends JpaRepository<OrderOfSystem, Long> {

    List<OrderOfSystem> findAll();

    OrderOfSystem save(OrderOfSystem orderOfSystem);
}
