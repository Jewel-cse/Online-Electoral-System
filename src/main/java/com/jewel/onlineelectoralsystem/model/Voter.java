package com.jewel.onlineelectoralsystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Voter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer voterId;
    private String password;
    private String name;
    private boolean voted;

    //########### constructors
    public Voter() {

    }
    public Voter(Boolean voted){
        this.voted = voted;
    }

    public Voter(Integer id, Integer voterId, String password, String name) {
        this(false);
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

    public boolean isVoted() {
        return voted;
    }

    public void setVoted(boolean voted) {
        this.voted = voted;
    }

    @Override
    public String toString() {
        return "Voter{" +
                "id=" + id +
                ", voterId=" + voterId +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", voted=" + voted +
                '}';
    }
}
