package com.reljicd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.reljicd.model.Analyst;

public interface AnalystRepository extends JpaRepository<Analyst, Long> {

    @Query(nativeQuery = true, value = "select a.* from Analyst a join user_of_system u on u.id=a.user_of_system_id where u.username = :username")
    Analyst findByUsername(@Param("username") String username);
}
