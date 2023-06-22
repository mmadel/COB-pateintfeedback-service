package com.cob.feedback.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "clinic")
@Setter
@Getter
public class ClinicEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="clinic_name")
    private String name;
    @Column(name = "address")
    private String address;
}
