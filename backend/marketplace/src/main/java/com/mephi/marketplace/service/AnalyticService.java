package com.mephi.marketplace.service;


import com.mephi.marketplace.model.Analyst;
import com.mephi.marketplace.model.AnalyticReport;

public interface AnalyticService {

    AnalyticReport loadAnalyticReport(Byte[] blob, Analyst analyst);

}

