package org.example.dao;


import lombok.Getter;
import lombok.Setter;
import org.example.enums.PersonType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name="persons")
public class Person implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private long id;
    @Column(unique=true, nullable=false,name="login")
    private String login;
    @Getter
    @Setter
    @Column(name="pass")
    private String password;
    @Getter
    @Setter
    @Column(name="first_name")
    private String firstName;
    @Getter
    @Setter
    @Column(name="last_name")
    private String lastName;
    @Column(name="birth_date")
    @Getter
    @Setter
    private Date dateOfBirth;
    @Getter
    @Setter
    @Column(name="phone")
    private String phone;
    @Getter
    @Setter
    @Column(unique = true,name="email")
    private String email;
    @Getter
    @Setter
    @ManyToMany
    @JoinTable(name="Person_Role",joinColumns = @JoinColumn(name="person_id",referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name="role_id",referencedColumnName = "id"))
    private Set<Role> roles;
    @Getter
    @Setter
    @OneToMany(mappedBy = "person")
    private Set<Client> clients;

    @Getter
    @Setter
    private PersonType personType;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
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

}
