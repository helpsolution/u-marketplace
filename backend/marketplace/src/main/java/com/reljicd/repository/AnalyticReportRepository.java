package com.reljicd.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.reljicd.model.Analyst;
import com.reljicd.model.AnalyticReport;
import com.reljicd.model.AnalyticReport;

public interface AnalyticReportRepository extends JpaRepository<AnalyticReport, Long> {

    @Query(nativeQuery = true,
           value = "select ar.* from Analytic_Report ar join analyst an on ar.analyst_id=an.user_of_system_id " +
                                        " join user_of_system uos on uos.id=an.user_of_system_id " +
                    "where uos.username = :login order by ar.id desc")
    Collection<AnalyticReport> findAllForAnalyst(@Param("login") String login);
}
