package com.jewel.onlineelectoralsystem.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.jewel.onlineelectoralsystem.model.Candidate;
import com.jewel.onlineelectoralsystem.model.OurUsers;
import com.jewel.onlineelectoralsystem.model.Voter;

import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReqRes {
    private int statusCode;
    private String error;
    private String message;
    private String token;
    private String refreshToken;
    private String expireTime;
    private String role;
    private String name;
    private String password;
    private String voterId;
    private boolean voted;

    private  String positionId;
    private String symbol;
    private Integer numberOfVote;

    private Integer getVote;
    private Integer TotalVoteCast;
    private double percentageOfVote;

    private List<Voter> voters;

    private List<Candidate> candidates;

    private OurUsers ourUsers;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(String expireTime) {
        this.expireTime = expireTime;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVoterId() {
        return voterId;
    }

    public void setVoterId(String voterId) {
        this.voterId = voterId;
    }

    public boolean isVoted() {
        return voted;
    }

    public void setVoted(boolean voted) {
        this.voted = voted;
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

    public Integer getNumberOfVote() {
        return numberOfVote;
    }

    public void setNumberOfVote(Integer numberOfVote) {
        this.numberOfVote = numberOfVote;
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

    public List<Voter> getVoters() {
        return voters;
    }

    public void setVoters(List<Voter> voters) {
        this.voters = voters;
    }

    public List<Candidate> getCandidates() {
        return candidates;
    }

    public void setCandidates(List<Candidate> candidates) {
        this.candidates = candidates;
    }

    public OurUsers getOurUsers() {
        return ourUsers;
    }

    public void setOurUsers(OurUsers ourUsers) {
        this.ourUsers = ourUsers;
    }
}
