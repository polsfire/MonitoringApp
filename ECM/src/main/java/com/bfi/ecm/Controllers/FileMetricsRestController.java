package com.bfi.ecm.Controllers;

import com.bfi.ecm.Entities.FileMetrics;
import com.bfi.ecm.Services.service_Interface.IFileMetricsService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/filemetrics")
@RequiredArgsConstructor
public class FileMetricsRestController {
    private final IFileMetricsService fileMetricsService;

    @Operation(description = "Add File Metrics")
    @PostMapping("/add")
    public FileMetrics addFileMetrics(@RequestBody FileMetrics fileMetrics) {
        return fileMetricsService.saveFileMetrics(fileMetrics);
    }

    @Operation(description = "Retrieve all File Metrics")
    @GetMapping("/all")
    public List<FileMetrics> retrieveAllFileMetrics() {
        return fileMetricsService.getAllFileMetrics();
    }

    @Operation(description = "Retrieve File Metrics by Id")
    @GetMapping("/{id}")
    public FileMetrics retrieveFileMetricsById(@PathVariable("id") Long id) {
        return fileMetricsService.getFileMetricsById(id);
    }

    @Operation(description = "Update File Metrics")
    @PutMapping("/update")
    public FileMetrics updateFileMetrics(@RequestBody FileMetrics fileMetrics) {
        return fileMetricsService.updateFileMetrics(fileMetrics);
    }

    @Operation(description = "Delete File Metrics by Id")
    @DeleteMapping("/delete/{id}")
    public void deleteFileMetrics(@PathVariable("id") Long id) {
        fileMetricsService.deleteFileMetrics(id);
    }



}
