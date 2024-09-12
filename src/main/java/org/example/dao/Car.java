package org.example.dao;

import org.example.enums.CarType;

import javax.persistence.*;

@Entity
@Table(name="cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="car_number")
    private String carNumber;
    @Column(name="car_make")
    private String make;
    @Column(name="car_maodel")
    private String model;
    @Column(name="car_type")
    private CarType carType;

    @ManyToOne
    private Person person;
}
