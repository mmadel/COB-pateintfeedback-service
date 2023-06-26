package com.cob.feedback.entity;

import com.cob.feedback.enums.UserRole;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Setter
@Getter
@Entity
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_name")
    private String name;
    @Column(name = "password")
    private String password;

    @Column(name = "user_address")
    private String address;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_role")
    private UserRole userRole;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_clinic",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "clinic_id"))
    private Set<ClinicEntity> clinics;
    @Column
    private Long createdAt;

    @PrePersist
    private void beforeSaving() {
        createdAt = new Date().getTime();
    }
    @PostUpdate
    private void beforeUpdating() {
        createdAt = new Date().getTime();
    }
}
