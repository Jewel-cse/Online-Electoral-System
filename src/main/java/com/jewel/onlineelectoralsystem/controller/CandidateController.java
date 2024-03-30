package com.jewel.onlineelectoralsystem.controller;

import com.jewel.onlineelectoralsystem.model.Candidate;
import com.jewel.onlineelectoralsystem.repository.CandidateRepository;
import com.jewel.onlineelectoralsystem.service.CandidateService;
import com.jewel.onlineelectoralsystem.service.VotePressesingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CandidateController {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private CandidateService candidateService;
    @Autowired
    VotePressesingService votePressesingService;

    //get all the candidate by position id
    @GetMapping("api/candidates/{positionId}")
    public List<Candidate> getAllCandidate(@PathVariable String positionId){
        return candidateRepository.findByPositionId(positionId);
    }

    //delete a candidate
    @DeleteMapping("api/candidates/{id}")
    public void removeACandidate(@PathVariable Integer id){
        candidateRepository.deleteById(id);
    }

    //add a candidate
    @PostMapping ("api/candidates")
    public Candidate addCandidate(@RequestBody Candidate candidate){
        if(!candidateService.isDuplicateCandidateIsAdded(candidate.getSymbol(),candidate.getPositionId())){
            System.out.println("Hello candidate is posting.........");
            votePressesingService.initializeVoteForCandidate(candidate.getPositionId(),candidate.getSymbol());
            return candidateRepository.save(candidate);
        }
        return null;
    }

    //Update a candidate
    @PutMapping("/api/candidates")
    public Candidate updateCandidate(@RequestBody Candidate candidate){
        if(!candidateService.isDuplicateCandidateIsAdded(candidate.getSymbol(),candidate.getPositionId())){
            //find existing -> delete it -> post updated candidate with initialization vote-cast
            Candidate temp = candidateRepository.findById(candidate.getId()).get();

            candidateRepository.delete(temp);

            votePressesingService.initializeVoteForCandidate(candidate.getPositionId(),candidate.getSymbol());
            return  candidateRepository.save(candidate);
        }
        return null;
    }
}
