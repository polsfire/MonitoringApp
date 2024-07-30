package com.bfi.ecm.Repositories;

import com.bfi.ecm.Entities.FileMetrics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileMetricsRepository extends JpaRepository<FileMetrics, Long> {
    // You can add custom query methods if needed
}
