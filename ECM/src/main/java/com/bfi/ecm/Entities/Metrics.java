package com.bfi.ecm.Entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Metrics {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  LocalDateTime processingStartTime;
  Long processingDuration;
  Integer numberOfErrors;
  String status;
  @ManyToOne
  User user;
  @ManyToOne
  FileMetrics filemetrics;
}
