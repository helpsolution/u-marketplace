package com.reljicd.model;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "analytic_report")
@Data
public class AnalyticReport {
    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "analyst_id")
    private Analyst analystId;

    @Column(name = "report_name")
    private String reportName;

    private byte[] report;
}
