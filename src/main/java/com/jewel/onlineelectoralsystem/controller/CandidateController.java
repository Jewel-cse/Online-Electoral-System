package com.jewel.onlineelectoralsystem.controller;

import com.jewel.onlineelectoralsystem.model.Candidate;
import com.jewel.onlineelectoralsystem.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
}
