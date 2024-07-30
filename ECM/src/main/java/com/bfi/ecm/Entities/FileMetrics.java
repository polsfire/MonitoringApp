package com.bfi.ecm.Entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class FileMetrics extends Metrics { // Corrected the class name it extends to Metric


    String directoryLocation; // Added correct field type and name
    String filePath; // Added correct field type and name
    String fileExtension; // Added correct field type and name
    @OneToMany(cascade = CascadeType.ALL, mappedBy="filemetrics")
    private Set<Metrics> Metricss;
}
