package com.jewel.onlineelectoralsystem.service;

import com.jewel.onlineelectoralsystem.model.VoteCount;
import com.jewel.onlineelectoralsystem.repository.CandidateRepository;
import com.jewel.onlineelectoralsystem.repository.VoteProcessRepository;
import com.jewel.onlineelectoralsystem.repository.VoterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VotePressesingService {
    @Autowired
    private  VoterRepository voterRepository;
    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    VoteProcessRepository voteProcessRepository;
    //save vote count initializations if any candidate is added/post
    public void initializeVoteForCandidate(String symbol,Integer numberOfVote ){
        voteProcessRepository.save(new VoteCount(symbol,numberOfVote));
    }
    public void processVote(String symbol, Integer voterId){

    }
}
