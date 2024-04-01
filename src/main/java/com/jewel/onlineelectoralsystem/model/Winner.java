package com.jewel.onlineelectoralsystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Winner {
    @Id
    private String positionId;
    private String symbol;
    private Integer getVote;
    private Integer TotalVoteCast;
    private double percentageOfVote;

    public Winner() {
    }

    public Winner(String positionId, String symbol, Integer getVote, Integer totalVoteCast, double percentageOfVote) {
        this.positionId = positionId;
        this.symbol = symbol;
        this.getVote = getVote;
        TotalVoteCast = totalVoteCast;
        this.percentageOfVote = percentageOfVote;
    }

    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Integer getGetVote() {
        return getVote;
    }

    public void setGetVote(Integer getVote) {
        this.getVote = getVote;
    }

    public Integer getTotalVoteCast() {
        return TotalVoteCast;
    }

    public void setTotalVoteCast(Integer totalVoteCast) {
        TotalVoteCast = totalVoteCast;
    }

    public double getPercentageOfVote() {
        return percentageOfVote;
    }

    public void setPercentageOfVote(double percentageOfVote) {
        this.percentageOfVote = percentageOfVote;
    }

    @Override
    public String toString() {
        return "Winner{" +
                "positionId='" + positionId + '\'' +
                ", symbol='" + symbol + '\'' +
                ", getVote=" + getVote +
                ", TotalVoteCast=" + TotalVoteCast +
                ", percentageOfVote=" + percentageOfVote +
                '}';
    }
}
