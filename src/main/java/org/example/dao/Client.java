package org.example.dao;



import lombok.*;
import org.example.enums.CarType;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name="clients")
public class Client {
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    @Getter
    @Setter
    @Column(name="name")
    String name;
    @Getter
    @Setter
    @Column(name="phone")
    int phone;
    @Getter
    @Setter
    @Column(name="car_number")
    private String carNumber;
    @Getter
    @Setter
    @Column(name="car_type")
    private CarType carType;
    @Getter
    @Setter
    @ManyToOne
    private Person person;
    @Getter
    @Setter
    @OneToMany(mappedBy = "client",cascade = CascadeType.ALL)
    private Set<Order> orders;
    @Getter
    @Setter
    @ManyToMany
    @JoinTable(name="Client_stations",joinColumns = @JoinColumn(name="client_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="station_id",referencedColumnName = "id"))
    private Set<Station> stations;



}
