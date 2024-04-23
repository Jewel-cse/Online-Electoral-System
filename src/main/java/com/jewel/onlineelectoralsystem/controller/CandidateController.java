package com.jewel.onlineelectoralsystem.controller;

import com.jewel.onlineelectoralsystem.dto.ReqRes;

import com.jewel.onlineelectoralsystem.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CandidateController {

    @Autowired
    private CandidateService candidateService;

    //get all the candidate by position id
    @GetMapping("api/v1/admin-user/candidates/{positionId}")
    public ResponseEntity<Object> getAllCandidate(@PathVariable String positionId){
        return ResponseEntity.ok(candidateService.getCandidate(positionId)) ;
    }

//    @GetMapping("api/v1/admin/candidates/id/{id}")
//    public ResponseEntity<Object> getCandidate(@PathVariable Integer id){
//        return ResponseEntity.ok(candidateRepository.findById(id).orElse(null));
//    }

    //delete a candidate
    @DeleteMapping("api/v1/secure/admin/candidates/{id}")
    public ResponseEntity<Object> removeACandidate(@PathVariable Integer id){
        return ResponseEntity.ok(candidateService.deleteCandidate(id));
    }

    //add a candidate
    @PostMapping ("api/v1/secure/admin/candidates")
    public ResponseEntity<Object> addCandidate(@RequestBody ReqRes candidateRequest){
        return  ResponseEntity.ok(candidateService.postCandidate(candidateRequest));
    }

    //Update a candidate
    @PutMapping("/api/v1/secure/admin/candidates/{id}")
    public ResponseEntity<Object> updateCandidate(@PathVariable Integer id,@RequestBody ReqRes candidateRequest){
        return ResponseEntity.ok(candidateService.updateCandidate(id,candidateRequest));
    }
}
