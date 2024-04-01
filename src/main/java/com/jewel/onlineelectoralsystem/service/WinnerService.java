package com.jewel.onlineelectoralsystem.service;

import com.jewel.onlineelectoralsystem.model.VoteCount;
import com.jewel.onlineelectoralsystem.model.Winner;
import com.jewel.onlineelectoralsystem.repository.WinnerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class WinnerService extends  ElectionResultService{
    @Autowired
    private WinnerRepo winnerRepo;

    //store a single winner database...
    public void storeWinner(String positionId){

        VoteCount winnerVoteCount =  getResult(positionId).stream()
                .max(Comparator.comparingInt(VoteCount::getNumberOfVote))
                .orElse(null);

        if(winnerVoteCount == null)
            return ;

        Integer totalVoteCast = getTotalVoteCast(positionId);
        double percentage  = (double) (winnerVoteCount.getNumberOfVote() * 100)/totalVoteCast;
        percentage = Math.round(percentage * 1000.0) / 1000.0;  // format percentage
        winnerRepo.save(new Winner(positionId,winnerVoteCount.getId().getSymbol(), winnerVoteCount.getNumberOfVote(), totalVoteCast,percentage));
    }
    //store into database
    public void storeAllWinner(){
        storeWinner("mp");
        storeWinner("member");
        storeWinner("chairman");
        storeWinner("president");
    }

    //get all the winner list from the database
    public List<Winner> getAllWinner(){
        return winnerRepo.findAll();
    }
}
