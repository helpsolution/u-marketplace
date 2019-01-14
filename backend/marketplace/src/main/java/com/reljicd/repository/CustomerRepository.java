package com.reljicd.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reljicd.model.Analyst;
import com.reljicd.model.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findById(Long id);

    @Query(nativeQuery = true, value = "select c.* from customer c join user_of_system u on c.user_of_system_id = u.id where u.username= :name")
    Customer findCustomerByName(@Param("name") String name);
}
