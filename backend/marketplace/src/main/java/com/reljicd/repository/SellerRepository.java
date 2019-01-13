package com.reljicd.repository;

import com.reljicd.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface SellerRepository extends JpaRepository<Seller, Long> {
    Optional<Seller> findById(Long id);

    @Query("select s from Seller s inner join s.user u where u.username = :username")
    Optional<Seller> findByUsername(@Param("username") String username);

}
