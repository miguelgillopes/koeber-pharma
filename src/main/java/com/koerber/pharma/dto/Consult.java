package com.koerber.pharma.dto;

import jakarta.persistence.*;


@Entity
@Table(name = "consult")
public class Consult {

    @Id
    private Long id;

    private String doctor;
    private String specialty;
    private String patient;
    private Integer patient_age;
    private String pathology;
    private String symptoms;
}
