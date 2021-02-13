package com.university.lsd.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Data
@Entity
public class Address {

    @Id
    @JsonIgnore
    @GeneratedValue
    private UUID id;
    private String country;
    private String city;
    private String street;
}
