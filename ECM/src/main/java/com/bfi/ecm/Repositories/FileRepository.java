package com.bfi.ecm.Repositories;

import com.bfi.ecm.Entities.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<File, Long> {
    // You can add custom query methods if needed
}
