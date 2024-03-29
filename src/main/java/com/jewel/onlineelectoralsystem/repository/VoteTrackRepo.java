package com.jewel.onlineelectoralsystem.repository;

import com.jewel.onlineelectoralsystem.model.VoteRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VoteTrackRepo extends JpaRepository<VoteRecord,Integer> {
    Optional<VoteRecord> findByVoterId(Integer voterId);
}
