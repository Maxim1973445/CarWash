package org.example.dao;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="owners")
@Getter
@Setter
public class Owner {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Person person;
    @OneToOne
    private Station station;



}
