package org.example.dao;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.enums.OrderStatus;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name= "orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    private LocalDateTime orderDate;
    @ManyToOne(cascade = CascadeType.MERGE)
    private Car car;
    @ManyToOne(cascade = CascadeType.MERGE)
    private Person person;
    @ManyToOne(cascade = CascadeType.MERGE)
    private StationService service;
    @ManyToOne(cascade = CascadeType.MERGE)
    private Station station;
}

