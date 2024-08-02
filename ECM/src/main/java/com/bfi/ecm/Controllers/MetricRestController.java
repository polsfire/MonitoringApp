package com.bfi.ecm.Controllers;


import com.bfi.ecm.Entities.Metrics;
import com.bfi.ecm.Services.service_Interface.IMetricsService;
 import io.swagger.v3.oas.annotations.Operation;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;

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
    public ResponseEntity<Map<String, Object>> receiveParameters(@RequestBody Map<String, String[]> parameterValues) {
        // Initialize response map
        Map<String, Object> response = new HashMap<>();

        // Wrapper to store parameters from the first condition block
        final ListWrapper paramsWrapper = new ListWrapper();

        // Log received parameters
        if (parameterValues != null) {
            parameterValues.forEach((key, values) -> {
                if (values != null) {
                    // Log parameter name and values
                    System.out.print("Parameter: " + key + " | Values: ");
                    for (String value : values) {
                        System.out.print(value + " ");
                    }
                    System.out.println(); // New line for readability

                    // Process parameters based on the key
                    if ("number of specific type file".equals(key)) {
                        try {
                            // Convert values to a list
                            paramsWrapper.setParams(Arrays.asList(values));
                            // Call the service and get the result
                            System.out.println("the params : " + paramsWrapper.getParams());
                            String jsonResult = metricsService.logfind(paramsWrapper.getParams());
                            // Log and add result to the response
                            System.out.println("JSON Result: " + jsonResult);
                            response.put("findlog_result", jsonResult);
                        } catch (IOException e) {
                            System.err.println("Error processing 'number of specific type file' parameter: " + e.getMessage());
                            response.put("error", "Error processing 'number of specific type file' parameter");
                        }
                    } else if ("errors".equals(key)) {
                        try {
                            // Convert values to a list
                            List<String> params2 = Arrays.asList(values);
                            System.out.println("the params2 : " + params2);
                            // Ensure params from the previous block are available
                            if (paramsWrapper.getParams() != null) {
                                String jsonResult = metricsService.errormetric(paramsWrapper.getParams(), params2);
                                System.out.println("JSON Result: " + jsonResult);
                                response.put("errormetric_result", jsonResult);
                            } else {
                                System.err.println("Params from 'number of specific type file' not available.");
                                response.put("error", "Params from 'number of specific type file' not available.");
                            }
                        } catch (IOException e) {
                            System.err.println("Error processing 'error' parameter: " + e.getMessage());
                            response.put("error", "Error processing 'error' parameter");
                        }
                    }
                }
            });
        }

        // Return the response
        return ResponseEntity.ok(response);
    }

    // Wrapper class to hold the list
    @Setter
    @Getter
    private static class ListWrapper {
        private List<String> params;

    }

}
