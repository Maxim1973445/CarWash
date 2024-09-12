package org.example.dao;

import lombok.*;
import org.example.enums.CarType;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;


@Entity
@Table(name="clients")
@Getter
@Setter
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="phone")
    private String phone;
    @Column(name="car_number")
    private String carNumber;
    @Column(name="car_type")
    private CarType carType;
    @Column(name="car_mark")
    private CarType carMark;
    @ManyToOne
    private Person person;
    @OneToMany(mappedBy = "client",cascade = CascadeType.ALL)
    private Set<Order> orders;

    @ManyToMany
    @JoinTable(name="Client_stations",joinColumns = @JoinColumn(name="client_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="station_id",referencedColumnName = "id"))
    private Set<Station> stations;
}
