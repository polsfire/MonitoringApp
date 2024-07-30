package com.bfi.ecm.Services.service_Interface;

import com.bfi.ecm.Entities.FileMetrics;

import java.io.IOException;
import java.util.List;

public interface IFileMetricsService {
    FileMetrics saveFileMetrics(FileMetrics fileMetrics);
    FileMetrics getFileMetricsById(Long id);
    List<FileMetrics> getAllFileMetrics();
    FileMetrics updateFileMetrics(FileMetrics fileMetrics);
    void deleteFileMetrics(Long id);
}
