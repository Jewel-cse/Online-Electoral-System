package com.jewel.onlineelectoralsystem.model;
public class Result {
    //total vote cast for @@ president
    //total vote cast for @@ mp
    //total vote cast for @@ chairman
    //total vote cast for @@ member

    private Integer totalPresidentVote;
    private Integer totalMPVote;
    private Integer totalChairmanVote;
    private Integer totalMemberVote;

    static Integer totalCast = 0;

    public Result() {
    }

    public Result(Integer totalPresidentVote, Integer totalMPVote, Integer totalChairmanVote, Integer totalMemberVote) {
        this.totalPresidentVote = totalPresidentVote;
        this.totalMPVote = totalMPVote;
        this.totalChairmanVote = totalChairmanVote;
        this.totalMemberVote = totalMemberVote;
    }

    @Override
    public String toString() {
        return "Result{" +
                "totalPresidentVote=" + totalPresidentVote +
                ", totalMPVote=" + totalMPVote +
                ", totalChairmanVote=" + totalChairmanVote +
                ", totalMemberVote=" + totalMemberVote +
                '}';
    }
}
