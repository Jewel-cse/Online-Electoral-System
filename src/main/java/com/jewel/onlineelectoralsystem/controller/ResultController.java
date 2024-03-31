package com.jewel.onlineelectoralsystem.controller;

import com.jewel.onlineelectoralsystem.model.Result;
import com.jewel.onlineelectoralsystem.model.VoteCount;
import com.jewel.onlineelectoralsystem.service.ElectionResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ResultController {
    //get result
    @Autowired
    private ElectionResultService electionResultService;
    @GetMapping("/api/election-result/{positionId}")
    public ResponseEntity<List<VoteCount>> getResult(@PathVariable String positionId){

        Result res = new Result(electionResultService.getTotalCastPresidentVote()
                , electionResultService.getTotalCastMPVote()
                , electionResultService.getTotalCastChairmanVote()
                , electionResultService.getTotalCastMemberVote());
        System.out.println(res.toString());
        return new ResponseEntity<List<VoteCount>>(electionResultService.getResult(positionId), HttpStatus.OK);
    }
}
