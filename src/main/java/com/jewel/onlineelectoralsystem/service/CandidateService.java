package com.jewel.onlineelectoralsystem.service;

import com.jewel.onlineelectoralsystem.model.VoteCount;
import com.jewel.onlineelectoralsystem.repository.CandidateRepository;
import com.jewel.onlineelectoralsystem.repository.VoteCountRepository;
import com.jewel.onlineelectoralsystem.utility.VoteCountKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CandidateService {
    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private  VoteCountRepository voteCountRepository;
    public boolean isDuplicateCandidateIsAdded(String symbol,String positionId){
        VoteCount existsCandidate = voteCountRepository.findById(new VoteCountKey(positionId,symbol)).orElse(null);
        if(existsCandidate == null){
            return false;
        }
        return true;
    }
}
