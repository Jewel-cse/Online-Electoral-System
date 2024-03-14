package com.jewel.onlineelectoralsystem.service;

import com.jewel.onlineelectoralsystem.model.VoteCount;
import com.jewel.onlineelectoralsystem.model.Voter;
import com.jewel.onlineelectoralsystem.repository.CandidateRepository;
import com.jewel.onlineelectoralsystem.repository.VoteProcessRepository;
import com.jewel.onlineelectoralsystem.repository.VoterRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VotePressesingService {
    @Autowired
    private  VoterRepository voterRepository;
    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    VoteProcessRepository voteProcessRepository;

    @PersistenceContext
    private  EntityManager entityManager;

    //save vote count initializations if any candidate is added/post
    public void initializeVoteForCandidate(String symbol,Integer numberOfVote ){
        voteProcessRepository.save(new VoteCount(symbol,numberOfVote));
    }
    public void processVote(String symbol, Integer voterId){
        //voterId->symbol: isVoted- true, and number of vote++;
        Optional<VoteCount> existingVoteCount = voteProcessRepository.findBySymbol(symbol);
        Optional<Voter> voter = voterRepository.findByVoterId(voterId);
        if(existingVoteCount.isPresent() && !voter.get().isVoted()){
             // increament vote
             int count = existingVoteCount.get().getNumberOfVote();
             existingVoteCount.get().setNumberOfVote(count+1);
             voteProcessRepository.save(existingVoteCount.get());

             // make true the is voted field of voter
             voter.get().setVoted(true);
             voterRepository.save(voter.get());
        }
    }
}
