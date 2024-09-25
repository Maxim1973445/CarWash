package org.example.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name= "stationservices")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
