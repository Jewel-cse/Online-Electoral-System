package com.jewel.onlineelectoralsystem.service;

import com.jewel.onlineelectoralsystem.model.Candidate;
import com.jewel.onlineelectoralsystem.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CandidateService {
    @Autowired
    private CandidateRepository candidateRepository;

    public boolean isDuplicateCandidateIsAdded(String symbol){
        Optional<Candidate> existsCandidate = candidateRepository.findBySymbol(symbol);
        return existsCandidate.isPresent();
    }
}
