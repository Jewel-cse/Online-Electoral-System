package com.jewel.onlineelectoralsystem.controller;

import com.jewel.onlineelectoralsystem.service.VotePressesingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class VoteProcess {
    @Autowired
    private VotePressesingService votePressesingService;
    //cast vote
    @GetMapping("/api/cast-vote/{voterId}/{symbol}")
    public void castingVote(@PathVariable Integer voterId,@PathVariable String symbol){
        votePressesingService.processVote(symbol,voterId);
    }
}
