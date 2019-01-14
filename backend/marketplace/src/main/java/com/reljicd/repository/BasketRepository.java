package com.reljicd.repository;

import com.reljicd.model.Basket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

/**
 * @author aguminskaya
 * @since 2019-01-14
 */
public interface BasketRepository extends JpaRepository<Basket, Long> {

    List<Basket> findAll();

    Basket save(Basket basket);

    @Query(nativeQuery = true, value = "select b.* from basket b join customer c on b.customer_id=c.user_of_system_id " +
            "join user_of_system uos on uos.id=c.user_of_system_id where uos.username = :name and b.active=true ")
    Collection<Basket> findAllActiveBaskets(@Param("name") String name);
}
