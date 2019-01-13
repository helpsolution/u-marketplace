package com.reljicd.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.reljicd.model.Analyst;
import com.reljicd.model.User;

public interface AnalystRepository extends JpaRepository<Analyst, Long> {
}
