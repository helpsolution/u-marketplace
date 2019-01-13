package com.reljicd.repository;

import com.reljicd.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;



public interface SellerRepository extends JpaRepository<Seller, Long> {
    Seller findById(Long id);

    @Query(nativeQuery = true, value = "select s.* from Seller s join user_of_system u on u.id=s.user_of_system_id where u.username = :username")
    Seller findByUsername(@Param("username") String username);

}
