package com.jewel.onlineelectoralsystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class VoteCount {

    @Id
    private String positionId;
    private String symbol;
    private Integer numberOfVote;

    public VoteCount() {
    }

    public VoteCount(String symbol, String positionId, Integer numberOfVote) {
        this.symbol = symbol;
        this.positionId = positionId;
        this.numberOfVote = numberOfVote;
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
