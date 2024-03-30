package com.jewel.onlineelectoralsystem.utility;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class VoteCountKey implements Serializable {
    @NotNull
    private String positionId;
    @NotNull
    private String symbol;

    // Constructors, getters, and setters


    public VoteCountKey() {
    }

    public VoteCountKey(String positionId, String symbol) {
        this.positionId = positionId;
        this.symbol = symbol;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VoteCountKey that = (VoteCountKey) o;
        return Objects.equals(positionId, that.positionId) &&
                Objects.equals(symbol, that.symbol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(positionId, symbol);
    }
}
