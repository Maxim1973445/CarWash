package org.example.dao;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.enums.PersonType;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;
import java.util.Set;

@Entity
@Table(name="roles")
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private int id;
    @Getter
    @Setter
    private String description;
    @Getter
    @Setter
    @ManyToMany(mappedBy="roles")
    private List<Person> persons;



    @Override
    public String getAuthority() {
        return description;
    }
}
