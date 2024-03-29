package com.jewel.onlineelectoralsystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class VoteRecord {
    @Id
    private Integer voterId;
    private boolean castPresidentVote;
    private boolean castMPVote;
    private boolean castChairmanVote;
    private boolean castMemberVote;

    public VoteRecord(){}
    public VoteRecord(Integer voterId, boolean castPresidentVote, boolean castMPVote,
                      boolean castChairmanVote, boolean castMemberVote) {
        this.voterId = voterId;
        this.castPresidentVote = castPresidentVote;
        this.castMPVote = castMPVote;
        this.castChairmanVote = castChairmanVote;
        this.castMemberVote = castMemberVote;
    }

    public Integer getVoterId() {
        return voterId;
    }

    public void setVoterId(Integer voterId) {
        this.voterId = voterId;
    }

    public boolean isCastPresidentVote() {
        return castPresidentVote;
    }

    public void setCastPresidentVote(boolean castPresidentVote) {
        this.castPresidentVote = castPresidentVote;
    }

    public boolean isCastMPVote() {
        return castMPVote;
    }

    public void setCastMPVote(boolean castMPVote) {
        this.castMPVote = castMPVote;
    }

    public boolean isCastChairmanVote() {
        return castChairmanVote;
    }

    public void setCastChairmanVote(boolean castChairmanVote) {
        this.castChairmanVote = castChairmanVote;
    }

    public boolean isCastMemberVote() {
        return castMemberVote;
    }

    public void setCastMemberVote(boolean castMemberVote) {
        this.castMemberVote = castMemberVote;
    }

    @Override
    public String toString() {
        return "VoteRecord{" +
                "voterId=" + voterId +
                ", castPresidentVote=" + castPresidentVote +
                ", castMPVote=" + castMPVote +
                ", castChairmanVote=" + castChairmanVote +
                ", castMemberVote=" + castMemberVote +
                '}';
    }
}
