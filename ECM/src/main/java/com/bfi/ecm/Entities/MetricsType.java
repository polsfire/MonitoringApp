package com.bfi.ecm.Entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class MetricsType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Integer numberOfErrors;
    Integer numberOfWarnings;
    float fileSize; // Changed to Integer
    @OneToOne(mappedBy="metricstype")
    private File file;
}
