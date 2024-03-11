package com.jewel.onlineelectoralsystem.repository;

import com.jewel.onlineelectoralsystem.model.Voter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VoterRepository extends JpaRepository<Voter,Integer> {
    Optional<Voter> findByVoterId(Integer voterId);
    void deleteByVoterId(Integer voterId);
}
