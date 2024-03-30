package com.jewel.onlineelectoralsystem.model;

import com.jewel.onlineelectoralsystem.utility.VoteCountKey;
import jakarta.persistence.*;

@Entity
public class VoteCount {

    @EmbeddedId
    private VoteCountKey id;
    private Integer numberOfVote;

    public VoteCount() {
    }

    public VoteCount(VoteCountKey id, Integer numberOfVote) {
        this.id = id;
        this.numberOfVote = numberOfVote;
    }

    //    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public String getSymbol() {
//        return symbol;
//    }
//
//    public void setSymbol(String symbol) {
//        this.symbol = symbol;
//    }
//    public String getPositionId() {
//        return positionId;
//    }
//
//    public void setPositionId(String positionId) {
//        this.positionId = positionId;
//    }


    public VoteCountKey getId() {
        return id;
    }

    public void setId(VoteCountKey id) {
        this.id = id;
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
                "id=" + id +
                ", numberOfVote=" + numberOfVote +
                '}';
    }
}
