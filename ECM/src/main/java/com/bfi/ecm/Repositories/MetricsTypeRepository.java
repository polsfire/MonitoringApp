package com.bfi.ecm.Repositories;

import com.bfi.ecm.Entities.MetricsType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MetricsTypeRepository extends JpaRepository<MetricsType, Long> {
    // You can add custom query methods if needed
}
