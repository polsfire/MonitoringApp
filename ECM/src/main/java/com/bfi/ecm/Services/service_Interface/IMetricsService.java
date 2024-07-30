package com.bfi.ecm.Services.service_Interface;

import com.bfi.ecm.Entities.Metrics;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface IMetricsService {
    Metrics saveMetrics(Metrics metrics);
    Optional<Metrics> getMetricsById(Long id);
    List<Metrics> getAllMetrics();
    Metrics updateMetrics(Metrics metrics);
    void deleteMetrics(Long id);
    public long logfind(List<String> values) throws IOException;



}
