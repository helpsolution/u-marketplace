package com.reljicd.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reljicd.model.Analyst;
import com.reljicd.model.AnalyticReport;

public interface AnalyticReportRepository extends JpaRepository<AnalyticReport, Long> {
}
