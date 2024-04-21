package com.jewel.onlineelectoralsystem.service;

import com.jewel.onlineelectoralsystem.dto.ReqRes;
import com.jewel.onlineelectoralsystem.model.VoteCount;
import com.jewel.onlineelectoralsystem.model.VoteRecord;
import com.jewel.onlineelectoralsystem.model.Voter;
import com.jewel.onlineelectoralsystem.repository.CandidateRepository;
import com.jewel.onlineelectoralsystem.repository.VoteCountRepository;
import com.jewel.onlineelectoralsystem.repository.VoteTrackRepo;
import com.jewel.onlineelectoralsystem.repository.VoterRepository;
import com.jewel.onlineelectoralsystem.utility.VoteCountKey;
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
    VoteCountRepository voteCountRepository;

    @Autowired
    VoteTrackRepo voteTrackRepo;

    //save vote count initializations if any candidate is added/post
    public void initializeVoteForCandidate(String positionId, String symbol ){
        voteCountRepository.save(new VoteCount(new VoteCountKey(positionId,symbol),0));
    }

    //voter track record initialize when post
    public void initializeVoteTrack(Integer voterId){
        voteTrackRepo.save(new VoteRecord(voterId,false,false,false,false));
    }

    //check if already voted for a particular position id;
    boolean isAlreadyVotedForPosition(Integer voterId,String positionId){
        VoteRecord voteRecord = voteTrackRepo.findByVoterId(voterId).orElse(null);
        switch (positionId){
            case "president":
                return voteRecord.isCastPresidentVote();
            case "mp":
                return voteRecord.isCastMPVote();
            case "chairman":
                return voteRecord.isCastChairmanVote();
            case "member":
                return voteRecord.isCastMemberVote();
            default:
                System.out.println("Position is out of scope");
        }
        return false;
    }
    public ReqRes processVote(Integer voterId,String positionId,String symbol){

        ReqRes response = new ReqRes();

        //voterId->symbol: isVoted- true, and number of vote++;
        VoteCount existingVoteCount = voteCountRepository.findById(new VoteCountKey(positionId,symbol)).orElse(null);
        VoteRecord voteRecord = voteTrackRepo.findByVoterId(voterId).orElse(null);
        Voter voter = voterRepository.findByVoterId(voterId).orElse(null);

        if(existingVoteCount == null || voteRecord == null || voter == null){
            response.setStatusCode(400);
            response.setMessage("Bad request");
            return response;
        }

        if(!voter.isVoted() && !isAlreadyVotedForPosition(voterId,positionId)){

             // increment vote
             int count = existingVoteCount.getNumberOfVote();
             existingVoteCount.setNumberOfVote(count+1);
             voteCountRepository.save(existingVoteCount);

             response.setNumberOfVote(count+1);
             response.setVoterId(voterId.toString());
             response.setPositionId(positionId);
             response.setSymbol(symbol);
             response.setStatusCode(200);
            //response.setMessage("In " + response.getPositionId() + "-election, voter "+response.getVoterId()+" pick -"+ response.getSymbol());
            response.setMessage("successfully cast vote");

            //check position id and make vote for particular position and save

            switch (positionId){
                case "president": voteRecord.setCastPresidentVote(true);voteTrackRepo.save(voteRecord);break;
                case "mp": voteRecord.setCastMPVote(true);voteTrackRepo.save(voteRecord);break;
                case "chairman": voteRecord.setCastChairmanVote(true);voteTrackRepo.save(voteRecord);break;
                case "member": voteRecord.setCastMemberVote(true);voteTrackRepo.save(voteRecord);break;
                default:
                    System.out.println("you select invalid position id");break;
            }
        }else{
            response.setStatusCode(409);
            response.setMessage("Already voted for this position");
        }
        //make voter.voted if all vote are completed
        if(voteRecord.isCastChairmanVote() && voteRecord.isCastMemberVote()
        && voteRecord.isCastMPVote() && voteRecord.isCastPresidentVote()){
            voter.setVoted(true);
            voterRepository.save(voter);
            response.setVoted(true);
        }
        return response;
    }
}
