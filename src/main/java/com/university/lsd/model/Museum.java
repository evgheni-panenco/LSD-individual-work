package com.university.lsd.model;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import lombok.Data;

@Data
@Entity
public class Museum {

    @Id
    @GeneratedValue
    private UUID id;
    private String name;

    @OneToOne
    @Cascade(CascadeType.ALL)
    private Address address;
    private String foundationYear;
    private Long capacity;
}
