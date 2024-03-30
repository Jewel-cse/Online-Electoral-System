package com.jewel.onlineelectoralsystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class VoteCount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String positionId;
    private String symbol;
    private Integer numberOfVote;

    public VoteCount() {
    }

    public VoteCount(String positionId, String symbol, Integer numberOfVote) {
        this.id = 0;
        this.positionId = positionId;
        this.symbol = symbol;
        this.numberOfVote = numberOfVote;
    }
    public VoteCount(Integer id, String positionId, String symbol, Integer numberOfVote) {
        this.id = id;
        this.positionId = positionId;
        this.symbol = symbol;
        this.numberOfVote = numberOfVote;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    public Integer getNumberOfVote() {
        return numberOfVote;
    }

    public void setNumberOfVote(Integer numberOfVote) {
        this.numberOfVote = numberOfVote;
    }

    @Override
    public String toString() {
        return "VoteCount{" +
                "symbol='" + symbol + '\'' +
                ", positionId='" + positionId + '\'' +
                ", numberOfVote=" + numberOfVote +
                '}';
    }

}
