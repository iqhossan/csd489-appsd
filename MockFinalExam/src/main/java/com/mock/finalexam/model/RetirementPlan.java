package com.mock.finalexam.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="retirement_plan")
public class RetirementPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long planId;
    @Column(nullable = false, unique = true)
    private String referenceNumber;
    @Column(nullable = false)
    private LocalDate enrollmentDate;
    @Column(nullable = false)
    private LocalDate retirementDate;
    @Column(nullable = true)
    private double monthlyContribution;

    @JsonBackReference
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name="employee_id")
    private Employee employee;

}
