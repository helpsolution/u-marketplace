package com.mephi.marketplace.model;

import java.time.LocalDateTime;

public class AnalyticReport {
    private Long id;
    private LocalDateTime createdAt;
    private Byte[] report;
    private Analyst analyst;

    public AnalyticReport() {
    }

    public AnalyticReport(Long id, LocalDateTime createdAt, Byte[] report, Analyst analyst) {
        this.id = id;
        this.createdAt = createdAt;
        this.report = report;
        this.analyst = analyst;
    }

    public void setAnalyst(Analyst analyst) {
        this.analyst = analyst;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Byte[] getReport() {
        return report;
    }

    public Analyst getAnalyst() {
        return analyst;
    }
}
