package com.jewel.onlineelectoralsystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class VoteCount {
    @Id
    private String symbol;
    private Integer numberOfVote;

    public VoteCount() {
    }

    public VoteCount(String symbol, Integer numberOfVote) {
        this.symbol = symbol;
        this.numberOfVote = numberOfVote;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
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
                ", numberOfVote=" + numberOfVote +
                '}';
    }
}
