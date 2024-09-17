package org.example.dao;


import lombok.Getter;
import lombok.Setter;
import org.example.enums.OrderStatus;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="orders")
@Getter
@Setter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private OrderStatus orderStatus;
    private String orderDate;
    @ManyToOne(cascade = CascadeType.ALL)
    private Person person;
    @ManyToOne(cascade = CascadeType.ALL)
    private StationService service;
    @ManyToOne(cascade = CascadeType.ALL)
    private Station station;
}

