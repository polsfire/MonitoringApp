package com.bfi.ecm.Repositories;

 import com.bfi.ecm.Entities.Metrics;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMetricsRepositorie extends JpaRepository<Metrics, Long> {
}
