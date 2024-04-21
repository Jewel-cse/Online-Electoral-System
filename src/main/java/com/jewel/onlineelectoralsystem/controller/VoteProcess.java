package com.jewel.onlineelectoralsystem.controller;

import com.jewel.onlineelectoralsystem.dto.ReqRes;
import com.jewel.onlineelectoralsystem.service.VotePressesingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class VoteProcess {
    @Autowired
    private VotePressesingService votePressesingService;
    //cast vote
//    @GetMapping("/api/cast-vote/{voterId}/{positionId}/{symbol}")
//    public void castingVoteOfSpecificPosition(@PathVariable Integer voterId,@PathVariable String positionId,@PathVariable String symbol){
//        votePressesingService.processVote(voterId,positionId,symbol);
//    }

    @PostMapping("api/v1/cast-vote")
    public ResponseEntity<Object> castVote(@RequestBody ReqRes voteData){
        return ResponseEntity.ok(votePressesingService.processVote(Integer.valueOf(voteData.getVoterId()),voteData.getPositionId(),voteData.getSymbol()));
    }
    public record VoteData(Integer voterId,String positionId,String symbol){}
}
