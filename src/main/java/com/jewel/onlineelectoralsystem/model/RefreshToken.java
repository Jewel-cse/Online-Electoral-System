package com.jewel.onlineelectoralsystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Date;
@Entity
public class RefreshToken {
    private String refreshToken;
    @Id
    private String voterId;

    public RefreshToken(){}
    public RefreshToken(String refreshToken, String voterId) {
        this.refreshToken = refreshToken;
        this.voterId = voterId;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getVoterId() {
        return voterId;
    }

    public void setVoterId(String voterId) {
        this.voterId = voterId;
    }

    @Override
    public String toString() {
        return "RefreshToken{" +
                "refreshToken='" + refreshToken + '\'' +
                ", voterId='" + voterId + '\'' +
                '}';
    }
}
