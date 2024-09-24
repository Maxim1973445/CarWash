package org.example.dao;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name= "stationservices")
@Getter
@Setter
public class StationService {
    @Id

    private Long id;
    @Column(name = "service_name")
    private String name;
    @Column(name="price")
    private int price;
    @OneToMany(mappedBy = "service")
    private List<Order> orders;
}
