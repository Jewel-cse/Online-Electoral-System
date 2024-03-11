package com.jewel.onlineelectoralsystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String position_id;
    private String name;

    //########## constructors

    public Candidate() {
    }

    public Candidate(String position_id, String name) {
        this.position_id = position_id;
        this.name = name;
    }

    //######  setter and getter
    public String getPosition_id() {
        return position_id;
    }

    public void setPosition_id(String position_id) {
        this.position_id = position_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
