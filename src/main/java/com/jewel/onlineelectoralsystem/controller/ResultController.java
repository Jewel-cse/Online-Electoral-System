package com.jewel.onlineelectoralsystem.controller;

import com.jewel.onlineelectoralsystem.model.Result;
import com.jewel.onlineelectoralsystem.model.VoteCount;
import com.jewel.onlineelectoralsystem.model.Winner;
import com.jewel.onlineelectoralsystem.service.ElectionResultService;
import com.jewel.onlineelectoralsystem.service.WinnerService;
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

    @Autowired
    private WinnerService winnerService;
    @GetMapping("/api/v1/election-result/{positionId}")
    public ResponseEntity<Object> getResult(@PathVariable String positionId){
        return ResponseEntity.ok(electionResultService.getResult(positionId));
    }

    @GetMapping("/api/v1/election-winners")
    public ResponseEntity<Object> getWinners(){
        winnerService.storeAllWinner();
        return ResponseEntity.ok(winnerService.getAllWinner());
    }
}
