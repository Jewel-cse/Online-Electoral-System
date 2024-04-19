package com.jewel.onlineelectoralsystem.controller;

import com.jewel.onlineelectoralsystem.service.VotePressesingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class VoteProcess {
    @Autowired
    private VotePressesingService votePressesingService;
    //cast vote
    @GetMapping("/api/cast-vote/{voterId}/{positionId}/{symbol}")
    public void castingVoteOfSpecificPosition(@PathVariable Integer voterId,@PathVariable String positionId,@PathVariable String symbol){
        votePressesingService.processVote(voterId,positionId,symbol);
    }

    @PostMapping("api/cast-vote")
    public void castVote(@RequestBody VoteData voteData){
        votePressesingService.processVote(voteData.voterId(),voteData.positionId(),voteData.symbol());
    }
    public record VoteData(Integer voterId,String positionId,String symbol){}
}
