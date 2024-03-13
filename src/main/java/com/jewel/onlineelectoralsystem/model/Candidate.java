package com.jewel.onlineelectoralsystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;

@Entity
public class Candidate {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String symbol;
    private String positionId;

    //########## constructors

    public Candidate() {
    }

    public Candidate(String name, String symbol, String positionId) {
        this.name = name;
        this.symbol = symbol;
        this.positionId = positionId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getPosition_id() {
        return positionId;
    }

    public void setPosition_id(String positionId) {
        this.positionId = positionId;
    }

    @Override
    public String toString() {
        return "Candidate{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", symbol='" + symbol + '\'' +
                ", positionId='" + positionId + '\'' +
                '}';
    }
}
