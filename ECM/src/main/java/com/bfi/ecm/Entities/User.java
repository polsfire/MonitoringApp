package com.bfi.ecm.Entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
@Entity
@Table(name = "app_user") // Change "app_user" to the desired table name
 public class User {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   private String username;
   private String firstname;
  private String Lastname;
  private String email;
  private String password;
    @OneToMany(cascade = CascadeType.ALL, mappedBy="user")
    private Set<Metrics> Metricss;

}

