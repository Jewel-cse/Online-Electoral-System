package com.jewel.onlineelectoralsystem.repository;

import com.jewel.onlineelectoralsystem.model.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate,Integer> {
    List<Candidate> findByPositionId(String positionId);

}
