package com.jewel.onlineelectoralsystem.controller;

import com.jewel.onlineelectoralsystem.model.Candidate;
import com.jewel.onlineelectoralsystem.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CandidateController {

    @Autowired
    private CandidateRepository candidateRepository;


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
    public void addCandidate(@RequestBody Candidate candidate){
        candidate.setId(null);
        candidateRepository.save(candidate);
    }

    //Update a candidate
    @PutMapping("/api/candidates")
    public Candidate updateCandidate(@RequestBody Candidate candidate){
        Candidate temp = candidateRepository.findById(candidate.getId()).get();
        candidateRepository.delete(temp);
        return  candidateRepository.save(candidate);
    }
}
