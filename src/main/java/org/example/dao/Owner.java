package org.example.dao;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="owners")
public class Owner {
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    @Getter
    @Setter
    @OneToOne
    private Person person;
    @Getter
    @Setter
    @OneToOne
    private Station station;



}
