package org.example.dao;



import lombok.*;
import org.example.enums.CarType;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name="clients")
@Getter
@Setter
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(name="name")
    String name;

    @Column(name="phone")
    int phone;

    @Column(name="car_number")
    private String carNumber;

    @Column(name="car_type")
    private CarType carType;

    @ManyToOne
    private Person person;
    @OneToMany(mappedBy = "client",cascade = CascadeType.ALL)
    private Set<Order> orders;

    @ManyToMany
    @JoinTable(name="Client_stations",joinColumns = @JoinColumn(name="client_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="station_id",referencedColumnName = "id"))
    private Set<Station> stations;



}
