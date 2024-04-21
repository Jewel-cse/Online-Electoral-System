package com.jewel.onlineelectoralsystem.service;

import com.jewel.onlineelectoralsystem.dto.ReqRes;
import com.jewel.onlineelectoralsystem.model.Voter;
import com.jewel.onlineelectoralsystem.repository.VoterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VoterService {
    @Autowired
    private VoterRepository voterRepository;

    @Autowired
    private VotePressesingService votePressesingService;

    public  boolean isDuplicateVoterAdded(Integer voterId){
        Optional<Voter> existsVoter = voterRepository.findByVoterId(voterId) ;
        return existsVoter.isPresent();
    }

    //####### Get All voters #######
    public List<Voter> getVoterList() {
        return  voterRepository.findAll();
    }

    //###### Get specific Voters service #####
    public Voter getVoter(Integer id){
        return voterRepository.findById(id).orElse(null);
    }

    //######## post Voter service #######
    public ReqRes postVoter(ReqRes voterRequest){
        ReqRes response = new ReqRes();
        try{
            Integer voterId = Integer.valueOf(voterRequest.getVoterId());
            if(!isDuplicateVoterAdded(voterId)){
                votePressesingService.initializeVoteTrack(voterId);
                voterRepository.save(new Voter(null,voterId,voterRequest.getPassword(),voterRequest.getName()));
                response.setName(voterRequest.getName());
                response.setVoterId(voterRequest.getVoterId());
                response.setMessage("Successfully post the voter");
                response.setStatusCode(200);
            }
            else{
                response.setStatusCode(409);
                response.setMessage("already exists the voter");
            }
        }catch (Exception e){
            response.setStatusCode(500);
            response.setError(e.getMessage());
        }
        return response;
    }

    //###### update voter service ##########
    public ReqRes updateVoter(ReqRes voterRequest,Integer id){

        ReqRes response = new ReqRes();
        try{
            Voter tempVoter = voterRepository.findById(id).orElse(null);

            tempVoter.setName(voterRequest.getName());
            tempVoter.setPassword(voterRequest.getPassword());
            tempVoter.setVoted(voterRequest.isVoted());
            voterRepository.save(tempVoter);

            response.setMessage("successfully update voter");
            response.setStatusCode(200);

        }catch (Exception e){
            response.setStatusCode(500);
            response.setError(e.getMessage());
        }
        return response;
    }

    //####### delete voter service ########
    public ReqRes deleteVoter(Integer voterId){
        ReqRes response = new ReqRes();
        try{
            Voter delVoter = voterRepository.findByVoterId(voterId).orElse(null);
            if(delVoter != null){
                voterRepository.delete(delVoter);
                response.setMessage("Successfully Delete the voter");
                response.setStatusCode(200);

            }else{
                response.setMessage("Not exists the voter");
                response.setStatusCode(404);
            }
        }catch (Exception e){
            response.setStatusCode(500);
            response.setError(e.getMessage());
        }
        return response;
    }
}
