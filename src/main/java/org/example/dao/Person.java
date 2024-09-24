package org.example.dao;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.enums.RoleType;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.*;

@Getter
@Setter
@Entity
@Table(name= "persons")
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    @Id
    private Long id;

    @Column(unique=true, nullable=false,name="login")
    private String login;

    @Column(name="pass")
    private String password;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="birth_date")
    private LocalDate dateOfBirth;

    @Column(name="phone")
    private String phone;

    @Column(unique = true,name="email")
    private String email;

    @Column(name="role")
    @Enumerated(EnumType.STRING)
    private RoleType role;

    @OneToMany(mappedBy = "person")
    private List<Car> cars;

    @Override
    public String toString() {
        return login+" "+phone+" "+email;
    }

}
