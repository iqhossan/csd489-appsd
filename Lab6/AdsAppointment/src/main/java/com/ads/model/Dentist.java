package com.ads.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="dentist")
public class Dentist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dentistId;

    private String firstName;
    private String lastName;
    private String phoneNo;
    private String email;
    private String specialization;

    @OneToMany(mappedBy = "dentist")
    private List<Appointment> appointment;
}
