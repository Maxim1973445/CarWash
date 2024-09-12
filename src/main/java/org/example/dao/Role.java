package org.example.dao;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.enums.RoleType;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;
@Entity
@Table(name="roles")
@Getter
@Setter
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    @ManyToMany(mappedBy="roles")
    private List<Person> persons;
    @Column(name="roleType")
    public RoleType roleType;

    @Override
    public String getAuthority() {
        return description;
    }
}
