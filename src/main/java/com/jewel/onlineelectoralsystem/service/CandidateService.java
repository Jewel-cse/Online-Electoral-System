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

        ReqRes response = new ReqRes();

        Candidate candidateToUpdate = candidateRepository.findById(id).orElse(null);
        if(candidateToUpdate == null){
            response.setMessage("Not exists candidates");
            response.setStatusCode(409);
            return response;
        }
        candidateRepository.delete(candidateToUpdate);
        voteCountRepository.deleteById(new VoteCountKey(candidateToUpdate.getPositionId(),candidateToUpdate.getSymbol()));
        response = postCandidate(CandidateRequest);

        if(response.getStatusCode() == 200){
            response.setMessage("Successfully updated the candidate");
        }
        return response;
    }

    //############ Delete candidate service #########
    public ReqRes deleteCandidate(Integer id){
        ReqRes response = new ReqRes();
        try{
            Candidate candidateToDelete = candidateRepository.findById(id).orElse(null);
            if(candidateToDelete !=null){
                candidateRepository.delete(candidateToDelete);
                voteCountRepository.deleteById(new VoteCountKey(candidateToDelete.getPositionId(),candidateToDelete.getSymbol()));
                response.setMessage("Successfully Delete the candidate");
                response.setStatusCode(200);
            }else{
                response.setStatusCode(409);
                response.setMessage("Not exist candidate");
            }

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

    public Candidate getCandidateById(Integer id){
        return  candidateRepository.findById(id).orElse(null);
    }

}
