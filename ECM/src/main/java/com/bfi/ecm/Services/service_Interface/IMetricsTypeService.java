package com.bfi.ecm.Services.service_Interface;

import com.bfi.ecm.Entities.MetricsType;

import java.util.List;

public interface IMetricsTypeService {
    MetricsType saveMetricsType(MetricsType metricsType);
    MetricsType getMetricsTypeById(Long id);
    List<MetricsType> getAllMetricsTypes();
    MetricsType updateMetricsType(MetricsType metricsType);
    void deleteMetricsType(Long id);
}
