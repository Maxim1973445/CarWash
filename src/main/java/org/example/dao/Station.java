package org.example.dao;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="stations")
@Getter
@Setter
public class Station {
    @Id
    private Long id;
    @Column(name="station_name")
    private String stationName;
    @Column(name="life_cycle")
    private String lifeCycle;
    @Column(name="address")
    private String address;
    @Column(name="first_phone")
    private String firstPhone;
    @Column(name="second_phone")
    private String secondPhone;
    @Column(name="station_email")
    private String stationEmail;
    @Column(name="coordinates")
    private String coordinates;
    @Column(name="file_path")
    private String filePath;
    @OneToOne(cascade = CascadeType.ALL)
    private Person owner;
    @OneToMany(mappedBy = "station")
    private List<Order> orders;




}
