package com.bfi.ecm.Services.service_Implimentation;

import com.bfi.ecm.Entities.MetricsType;
import com.bfi.ecm.Repositories.MetricsTypeRepository;
import com.bfi.ecm.Services.service_Interface.IMetricsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MetricsTypeServiceImpl implements IMetricsTypeService {

    private final MetricsTypeRepository metricsTypeRepository;

    @Autowired
    public MetricsTypeServiceImpl(MetricsTypeRepository metricsTypeRepository) {
        this.metricsTypeRepository = metricsTypeRepository;
    }

    @Override
    public MetricsType saveMetricsType(MetricsType metricsType) {
        return metricsTypeRepository.save(metricsType);
    }

    @Override
    public MetricsType getMetricsTypeById(Long id) {
        Optional<MetricsType> metricsType = metricsTypeRepository.findById(id);
        return metricsType.orElse(null);
    }

    @Override
    public List<MetricsType> getAllMetricsTypes() {
        return metricsTypeRepository.findAll();
    }

    @Override
    public MetricsType updateMetricsType(MetricsType metricsType) {
        return metricsTypeRepository.save(metricsType);
    }

    @Override
    public void deleteMetricsType(Long id) {
        metricsTypeRepository.deleteById(id);
    }
}
