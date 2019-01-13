package com.reljicd.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reljicd.model.Analyst;
import com.reljicd.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
