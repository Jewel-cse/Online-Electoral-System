package com.jewel.onlineelectoralsystem.controller;

import com.jewel.onlineelectoralsystem.model.Voter;
import com.jewel.onlineelectoralsystem.repository.VoterRepository;
import com.jewel.onlineelectoralsystem.service.VoterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class VoterController {
    @Autowired
    private VoterRepository voterRepository;

    @Autowired
    private  VoterService voterService;

    //1: get voter
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
        if(!voterService.isDuplicateVoterAdded(voter.getVoterId())){
            voter.setId(null);
            return  voterRepository.save(voter);
        }
        return null; //try to post  duplicate voter.
    }

    //3: delete voter
    @DeleteMapping("/api/voters/{voterId}")
    public void removeVoter(@PathVariable Integer voterId){
        //System.out.println("voter id is : "+voterId);
        Voter tempVoter = voterRepository.findByVoterId(voterId).get();
        voterRepository.delete(tempVoter);
    }

    //4: update a voter info
    //request voter theke existing id ta find korte hobe
    //existing id ta request voter e add korte hobe
    //delete existing voter and add request voter
    @PatchMapping ("/api/voters")
    public Voter updateVoterInfo(@RequestBody Voter voter){
        Voter tempVoter = voterRepository.findByVoterId(voter.getVoterId()).get();

        voterRepository.delete(tempVoter);

        return  voterRepository.save(voter);
    }
}
