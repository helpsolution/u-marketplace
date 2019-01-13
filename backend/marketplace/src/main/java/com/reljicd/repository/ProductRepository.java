package com.reljicd.repository;

import com.reljicd.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findById(Long id);
    Product save(Product product);
    void delete(Product product);
//    void update(Product product);


    @Query(nativeQuery = true, value = "select p.* from Product p join seller s on p.seller_id=s.user_of_system_id " +
            "join user_of_system uos on uos.id=s.user_of_system_id where uos.username = :login")
    Collection<Product> findAllForSeller(@Param("login") String login);

}
