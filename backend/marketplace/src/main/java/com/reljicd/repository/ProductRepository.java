package com.reljicd.repository;

import com.reljicd.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findById(Long id);
    Product save(Product product);
    void delete(Product product);


    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "update Product set seller_id = :sellerId, product_name = :productName, product_category = :productCategory, price = :price, description = :description, product_color = :productColor, product_size = :productSize, product_count = :productCount  where id = :id")
    void saveAndFlush(@Param("id") Long id, @Param("sellerId") Long sellerId, @Param("productName") String productName, @Param("productCategory") Long productCategory, @Param("price") BigDecimal price, @Param("description") String description, @Param("productColor") String productColor, @Param("productSize") String productSize, @Param("productCount") Integer productCount);
//    void update(Product product);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "update Product set product_count=product_count-1 where id = :id")
    void updateQuantity(@Param("id") Long id);

    @Query(nativeQuery = true, value = "select p.product_count from Product p where id = :id")
    Integer findQuantity(@Param("id") Long id);

    @Query(nativeQuery = true, value = "select p.* from Product p join seller s on p.seller_id=s.user_of_system_id " +
            "join user_of_system uos on uos.id=s.user_of_system_id where uos.username = :login order by p.id asc")
    Collection<Product> findAllForSeller(@Param("login") String login);

//    @Modifying
//    @Transactional
//    @Query(nativeQuery = true, value = "update Product set seller_id where id = :id")
//    void updateProduct(@Param("id") Long id, );

}
