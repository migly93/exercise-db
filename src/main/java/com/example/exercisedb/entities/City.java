package com.example.exercisedb.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cities")
@Data
public class City {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;
}
