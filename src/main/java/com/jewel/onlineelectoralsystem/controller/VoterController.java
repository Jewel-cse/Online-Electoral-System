package com.jewel.onlineelectoralsystem.controller;

import com.jewel.onlineelectoralsystem.model.Voter;
import com.jewel.onlineelectoralsystem.repository.VoterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class VoterController {
    //1: get all voter
    @Autowired
    private VoterRepository voterRepository;
    @GetMapping("/api/voter-list")
    public ResponseEntity<List<Voter>> getVoterList(){
        return new ResponseEntity<>(voterRepository.findAll(), HttpStatus.OK);
    }
    @GetMapping("/api/voters/{voterId}")
    public Optional<Voter> getVoterByVoterId(@PathVariable Integer voterId){
        return  voterRepository.findByVoterId(voterId);
    }
    //2: post voter
    @PostMapping("/api/voters")
    public Voter  addVoter(@RequestBody Voter voter){
        System.out.println(voter.toString());
        voter.setId(null);
        return  voterRepository.save(voter);
    }

    //3: delete voter
    @DeleteMapping("/api/voters/{voterId}")
    public void removeVoter(@PathVariable Integer voterId){
        //System.out.println("voter id is : "+voterId);
        Voter tempVoter = voterRepository.findByVoterId(voterId).get();
        voterRepository.delete(tempVoter);
    }
}
