package com.reljicd.repository;

import com.reljicd.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findById(Long id);
    Product save(Product product);
    void delete(Product product);
//    void update(Product product);
    List<Product> findAllForSeller(String login);


    @Query("select p from Product p inner join Seller s on p.seller_id=s.user_of_system_id join User uos on s.user_of_system_id=uos.id where uos.username = :login")
    Collection<Product> findByLogin(@Param("login") String login);

}
