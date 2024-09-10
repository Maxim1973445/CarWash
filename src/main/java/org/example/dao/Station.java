package org.example.dao;

import javax.persistence.*;

import java.util.List;

@Entity
@Table(name="stations")
public class Station {
    @Id
    private long id;
    @Column(name="station_name")
    private String stationName;
    @Column(name="life_cycle")
    private String lifeCycle;
    @Column(name="address")
    private String address;
    @Column(name="first_phone")
    private String firstPhone;
    @Column(name="second_phone")
    private String secondPhone;
    @Column(name="station_email")
    private String stationEmail;
    @Column(name="coordinates")
    private String coordinates;
    @Column(name="file_path")
    private String filePath;
    @OneToOne
    private Owner owner;
    @OneToMany(mappedBy = "station", cascade = CascadeType.ALL)
    private List<Order> orders;
    @ManyToMany(mappedBy = "stations")
    private List<Client> clients;



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getLifeCycle() {
        return lifeCycle;
    }

    public void setLifeCycle(String lifeCycle) {
        this.lifeCycle = lifeCycle;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFirstPhone() {
        return firstPhone;
    }

    public void setFirstPhone(String firstPhone) {
        this.firstPhone = firstPhone;
    }

    public String getStationEmail() {
        return stationEmail;
    }

    public void setStationEmail(String stationEmail) {
        this.stationEmail = stationEmail;
    }

    public String getSecondPhone() {
        return secondPhone;
    }

    public void setSecondPhone(String secondPhone) {
        this.secondPhone = secondPhone;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }
}
