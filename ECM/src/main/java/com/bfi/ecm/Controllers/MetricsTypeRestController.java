package com.bfi.ecm.Controllers;

import com.bfi.ecm.Entities.MetricsType;
import com.bfi.ecm.Services.service_Interface.IMetricsTypeService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/metricstypes")
@RequiredArgsConstructor
public class MetricsTypeRestController {
    private final IMetricsTypeService metricsTypeService;

    @Operation(description = "Add MetricsType")
    @PostMapping("/add")
    public MetricsType addMetricsType(@RequestBody MetricsType metricsType) {
        return metricsTypeService.saveMetricsType(metricsType);
    }

    @Operation(description = "Retrieve all MetricsTypes")
    @GetMapping("/all")
    public List<MetricsType> retrieveAllMetricsTypes() {
        return metricsTypeService.getAllMetricsTypes();
    }

    @Operation(description = "Retrieve MetricsType by Id")
    @GetMapping("/{id}")
    public MetricsType retrieveMetricsTypeById(@PathVariable("id") Long id) {
        return metricsTypeService.getMetricsTypeById(id);
    }

    @Operation(description = "Update MetricsType")
    @PutMapping("/update")
    public MetricsType updateMetricsType(@RequestBody MetricsType metricsType) {
        return metricsTypeService.updateMetricsType(metricsType);
    }

    @Operation(description = "Delete MetricsType by Id")
    @DeleteMapping("/delete/{id}")
    public void deleteMetricsType(@PathVariable("id") Long id) {
        metricsTypeService.deleteMetricsType(id);
    }
}
