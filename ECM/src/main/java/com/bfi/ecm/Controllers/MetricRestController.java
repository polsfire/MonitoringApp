package com.bfi.ecm.Controllers;


import com.bfi.ecm.Entities.Metrics;
import com.bfi.ecm.Services.service_Interface.IMetricsService;
 import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/Metric")
@RequiredArgsConstructor
public class MetricRestController {
    private final IMetricsService metricsService;
    @Operation(description = "Add Metrics")
    @PostMapping("/add")
    public Metrics addMetric(@RequestBody Metrics metricsData) {
        return metricsService.saveMetrics(metricsData);
    }
    @Operation(description = "Retrieve all Metrics")
    @GetMapping("/all")
    public List<Metrics> retrieveAllMetrics() {
        return metricsService.getAllMetrics();
    }
    @Operation(description = "Retrieve Metrics by Id")
    @GetMapping("/get/{id}")
    public Metrics retrieveMetricById(@PathVariable("id") Long idMetric) {
        Optional<Metrics> metrics = metricsService.getMetricsById(idMetric);
        return metrics.orElse(null); // Return the metrics if present, otherwise return null
    }

    @Operation(description = "Update Metrics")
    @PutMapping("/update")
    public Metrics updateMetric(@RequestBody Metrics metricsData) {
        return metricsService.updateMetrics(metricsData);
    }

    @Operation(description = "Delete Metrics by Id")
    @DeleteMapping("/delete/{id}")
    public void removeMetric(@PathVariable("id") Long idMetric) {
        metricsService.deleteMetrics(idMetric);
    }
    @PostMapping("/send-parameters")
    public ResponseEntity<String> receiveParameters(@RequestBody Map<String, String[]> parameterValues) {
        // Logging received parameter values
        System.out.println("Received parameter values:");

        parameterValues.forEach((key, values) -> {
            System.out.print("Parameter: " + key + " | Values: ");
            for (String value : values) {
                System.out.print(value + " ");
            }
            System.out.println(); // New line for each parameter

            // Call specific service methods based on parameter names
            if ("findlog".equals(key)) {
                try {
                    long count = metricsService.logfind(List.of(values));
                    String result = "Number of files with extension " + values[1] + ": " + count;
                    System.out.println(result);

                } catch (IOException e) {
                    // Handle exception or rethrow
                    throw new RuntimeException("Error processing 'findlog' parameter", e);
                }
            }

            // Add additional conditions for other parameter names and corresponding methods
        });

        // Optionally perform further processing
        return ResponseEntity.ok("Parameters received and processed successfully");
    }

}
