package com.jewel.onlineelectoralsystem.service;

import com.jewel.onlineelectoralsystem.dto.ReqRes;
import com.jewel.onlineelectoralsystem.model.Candidate;
import com.jewel.onlineelectoralsystem.model.VoteCount;
import com.jewel.onlineelectoralsystem.repository.CandidateRepository;
import com.jewel.onlineelectoralsystem.repository.VoteCountRepository;
import com.jewel.onlineelectoralsystem.utility.VoteCountKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidateService {
    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private  VoteCountRepository voteCountRepository;

    @Autowired
    private  VotePressesingService votePressesingService;
    public boolean isDuplicateCandidateIsAdded(String symbol,String positionId){
        VoteCount existsCandidate = voteCountRepository.findById(new VoteCountKey(positionId,symbol)).orElse(null);
        if(existsCandidate == null){
            return false;
        }
        return true;
    }
    //############ Post service #############
    public ReqRes postCandidate(ReqRes CandidateRequest){
        ReqRes response = new ReqRes();
        try{
            if(!isDuplicateCandidateIsAdded(CandidateRequest.getSymbol(),CandidateRequest.getPositionId())){
                votePressesingService.initializeVoteForCandidate(CandidateRequest.getPositionId(),CandidateRequest.getSymbol());
                candidateRepository.save(new Candidate(null,CandidateRequest.getName(),CandidateRequest.getSymbol(), CandidateRequest.getPositionId())) ;
                response.setName(CandidateRequest.getName());
                response.setSymbol(CandidateRequest.getSymbol());
                response.setPositionId(CandidateRequest.getPositionId());
                response.setMessage("Successfully post the candidate");
                response.setStatusCode(200);
            }
        }catch (Exception e){
            response.setStatusCode(500);
            response.setError(e.getMessage());
        }
        return response;
    }

    //############ Update candidate service ########
    public ReqRes updateCandidate(Integer id,ReqRes CandidateRequest){

        Candidate temp = candidateRepository.findById(id).orElse(null);
        candidateRepository.delete(temp);
        ReqRes response = postCandidate(CandidateRequest);

        if(response.getStatusCode() == 200){
            response.setMessage("Successfully updated the candidate");
        }
        return response;
    }

    //############ Delete candidate service #########
    public ReqRes deleteCandidate(Integer id){
        ReqRes response = new ReqRes();
        try{
            candidateRepository.deleteById(id);
            response.setMessage("Successfully Delete the candidate");
            response.setStatusCode(200);
        }catch (Exception e){
            response.setStatusCode(500);
            response.setError(e.getMessage());
        }
        return response;
    }

    //########## Get candidate service ############
    public List<Candidate> getCandidate(String positionId){
        return  candidateRepository.findByPositionId(positionId);
    }

}
