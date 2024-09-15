package org.example.dao;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;
@Entity
@Table(name="roles")
@Getter
@Setter
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String description;
    @ManyToMany(mappedBy="roles")
    private List<Person> persons;



    @Override
    public String getAuthority() {
        return description;
    }
}
