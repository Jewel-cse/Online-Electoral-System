package com.jewel.onlineelectoralsystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Voter {
    @Id
    @GeneratedValue
    private Integer id;
    private Integer voterId;
    private String password;
    private String name;

    //########### constructors
    public Voter() {
    }

    public Voter(Integer id, Integer voterId, String password, String name) {
        this.id = id;
        this.voterId = voterId;
        this.password = password;
        this.name = name;
    }
    // ############# Setters and getters


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVoterId() {
        return voterId;
    }

    public void setVoterId(Integer voterId) {
        this.voterId = voterId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Voter{" +
                "Id=" + id +
                ", voterId=" + voterId +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
