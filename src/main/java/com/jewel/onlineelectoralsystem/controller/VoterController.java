package com.jewel.onlineelectoralsystem.controller;

import com.jewel.onlineelectoralsystem.dto.ReqRes;
import com.jewel.onlineelectoralsystem.service.VoterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class VoterController {

    @Autowired
    private  VoterService voterService;


    @GetMapping("/api/v1/voters")
    public ResponseEntity<Object> getVoterList(){
        return  ResponseEntity.ok(voterService.getVoterList());
    }

//    @GetMapping("/api/voters/voter-id/{voterId}")
//    public Optional<Voter> getVoterByVoterId(@PathVariable Integer voterId){
//        return  voterRepository.findByVoterId(voterId);
//    }
    @GetMapping("api/v1/voters/{id}")
    public ResponseEntity<Object> getVotersById(@PathVariable Integer id){
        return ResponseEntity.ok(voterService.getVoter(id));
    }


    @PostMapping("/api/v1/voters")
    public ResponseEntity<Object>  addVoter(@RequestBody ReqRes voterRequest){
        return ResponseEntity.ok(voterService.postVoter(voterRequest));
    }


    @DeleteMapping("/api/v1/voters/{voterId}")
    public ResponseEntity<Object> removeVoter(@PathVariable Integer voterId){
        return ResponseEntity.ok(voterService.deleteVoter(voterId));
    }


    @PatchMapping ("/api/v1/voters/{id}")
    public ResponseEntity<Object> updateVoterInfo(@RequestBody ReqRes voterRequest,@PathVariable Integer id){
        return ResponseEntity.ok(voterService.updateVoter(voterRequest,id));
    }
}
