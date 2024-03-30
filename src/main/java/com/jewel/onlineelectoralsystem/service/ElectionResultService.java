package com.jewel.onlineelectoralsystem.service;

import com.jewel.onlineelectoralsystem.model.VoteRecord;
import com.jewel.onlineelectoralsystem.repository.VoteTrackRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ElectionResultService {
    @Autowired
    private VoteTrackRepo voteTrackRepo;

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

}