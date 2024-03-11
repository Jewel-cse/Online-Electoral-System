package com.jewel.onlineelectoralsystem.repository;

import com.jewel.onlineelectoralsystem.model.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate,Integer> {
}
