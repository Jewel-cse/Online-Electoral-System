package com.jewel.onlineelectoralsystem.service;

import com.jewel.onlineelectoralsystem.dto.ReqRes;
import com.jewel.onlineelectoralsystem.model.VoteCount;
import com.jewel.onlineelectoralsystem.repository.VoteCountRepository;
import com.jewel.onlineelectoralsystem.repository.VoteTrackRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ElectionResultService {
    @Autowired
    private VoteTrackRepo voteTrackRepo;
    @Autowired
    private VoteCountRepository voteCountRepository;

    // This will return total cast MpVote,chairman vote, president vote, member vote
    public int getTotalCastMPVote() {
        return voteTrackRepo.findAll().stream()
                .mapToInt(voteRecord->{
                    int total = 0;
                    if(voteRecord.isCastMPVote())
                        total++;
                    return total;
                })
                .sum();
    }
    public int getTotalCastPresidentVote() {
        return voteTrackRepo.findAll().stream()
                .mapToInt(voteRecord->{
                    int total = 0;
                    if(voteRecord.isCastPresidentVote())
                        total++;
                    return total;
                })
                .sum();
    }
    public int getTotalCastChairmanVote() {
        return voteTrackRepo.findAll().stream()
                .mapToInt(voteRecord->{
                    int total = 0;
                    if(voteRecord.isCastChairmanVote())
                        total++;
                    return total;
                })
                .sum();
    }
    public int getTotalCastMemberVote() {
        return voteTrackRepo.findAll().stream()
                .mapToInt(voteRecord->{
                    int total = 0;
                    if(voteRecord.isCastMemberVote())
                        total++;
                    return total;
                })
                .sum();
    }

    public int getTotalVoteCast(String positionId){
        switch (positionId){
            case "mp":
                return getTotalCastMPVote();
            case "president":
                return getTotalCastPresidentVote();
            case "member":
                return getTotalCastMemberVote();
            case "chairman":
                return getTotalCastChairmanVote();
            default:
                System.out.println("Illegal position you entered!!!");
        }
        return 0;
    }

    //give the VoteCount : positionId, symbol, number of vote for a specific positionId.
    public List<VoteCount> getResult(String positionId){
        return  voteCountRepository.findByIdPositionId(positionId).orElse(null);
    }
}
