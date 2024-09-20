package org.example.dao;

import lombok.*;
import org.example.enums.CarType;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name="cars")

@Getter
@Setter
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="car_number", unique = true,nullable = false)
    private String carNumber;
    @Column(name="car_make")
    private String make;
    @Column(name="car_model")
    private String model;
    @Column(name="car_type")
    @Enumerated(EnumType.STRING)
    private CarType carType;
    @OneToMany(mappedBy = "car")
    private Set<Order> orders;

    @ManyToOne
    private Person person;
}
