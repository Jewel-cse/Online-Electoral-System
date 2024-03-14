package com.jewel.onlineelectoralsystem.service;

import com.jewel.onlineelectoralsystem.model.Voter;
import com.jewel.onlineelectoralsystem.repository.VoterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VoterService {
    @Autowired
    private VoterRepository voterRepository;

    public  boolean isDuplicateVoterAdded(Integer voterId){
        Optional<Voter> existsVoter = voterRepository.findByVoterId(voterId) ;
        return existsVoter.isPresent();
    }

}
