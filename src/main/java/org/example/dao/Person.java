package org.example.dao;


import lombok.Getter;
import lombok.Setter;
import org.example.enums.RoleType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@Entity
@Table(name="Persons")
public class Person implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

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
    private Date dateOfBirth;

    @Column(name="phone")
    private String phone;

    @Column(unique = true,name="email")
    private String email;


    @Column(name="role")
    @Enumerated(EnumType.STRING)
    private RoleType role;

    @OneToMany(mappedBy = "person")
    private Set<Order> orders;

    @OneToMany(mappedBy = "person")
    private List<Car> cars;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_"+role.toString()));
        return authorities;
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return login+" "+phone+" "+email;
    }

}
