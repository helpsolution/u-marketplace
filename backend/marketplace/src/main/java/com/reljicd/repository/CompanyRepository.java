package com.reljicd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.reljicd.model.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    Company findById(Long id);

    @Query(nativeQuery = true, value = "select c.* from company c join user_of_system u on c.user_of_system_id = u.id where u.username= :name")
    Company findCompanyByName(@Param("name") String name);
}
