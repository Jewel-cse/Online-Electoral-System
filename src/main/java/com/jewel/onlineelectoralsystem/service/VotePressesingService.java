package com.jewel.onlineelectoralsystem.service;

import com.jewel.onlineelectoralsystem.model.VoteCount;
import com.jewel.onlineelectoralsystem.model.VoteRecord;
import com.jewel.onlineelectoralsystem.model.Voter;
import com.jewel.onlineelectoralsystem.repository.CandidateRepository;
import com.jewel.onlineelectoralsystem.repository.VoteProcessRepository;
import com.jewel.onlineelectoralsystem.repository.VoteTrackRepo;
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

    @Autowired
    VoteTrackRepo voteTrackRepo;

    //save vote count initializations if any candidate is added/post
    public void initializeVoteForCandidate(String positionId, String symbol ){
        voteProcessRepository.save(new VoteCount(symbol,positionId,0));
    }

    //voter track record initialize when post
    public void initializeVoteTrack(Integer voterId){
        voteTrackRepo.save(new VoteRecord(voterId,false,false,false,false));
    }
    public void processVote(Integer voterId,String positionId,String symbol){
        //voterId->symbol: isVoted- true, and number of vote++;
        Optional<VoteCount> existingVoteCount = voteProcessRepository.findByPositionIdAndSymbol(positionId,symbol);
        Optional<VoteRecord> voteRecord = voteTrackRepo.findByVoterId(voterId);
        Optional<Voter> voter = voterRepository.findByVoterId(voterId);

        System.out.println(existingVoteCount);
        System.out.println(voteRecord);
        System.out.println(voter);

        if(existingVoteCount.isPresent() && !voter.get().isVoted()){
             // increament vote
             int count = existingVoteCount.get().getNumberOfVote();
             existingVoteCount.get().setNumberOfVote(count+1);
             voteProcessRepository.save(existingVoteCount.get());
        }
        //check position id and make vote for particular position and save
        if(voteRecord.isPresent()){
            switch (positionId){
                case "president": voteRecord.get().setCastPresidentVote(true);voteTrackRepo.save(voteRecord.get());break;
                case "mp": voteRecord.get().setCastMPVote(true);voteTrackRepo.save(voteRecord.get());break;
                case "chairman": voteRecord.get().setCastChairmanVote(true);voteTrackRepo.save(voteRecord.get());break;
                case "member": voteRecord.get().setCastMemberVote(true);voteTrackRepo.save(voteRecord.get());break;
                default:
                    System.out.println("you select invalid position id");break;
            }
        }
        //make voter.voted if all vote are completed
        if(voter.isPresent() && voteRecord.isPresent() && voteRecord.get().isCastChairmanVote() && voteRecord.get().isCastMemberVote()
        && voteRecord.get().isCastMPVote() && voteRecord.get().isCastPresidentVote()){
            voter.get().setVoted(true);
            voterRepository.save(voter.get());
        }
    }
}
