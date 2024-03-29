package com.jewel.onlineelectoralsystem.repository;

import com.jewel.onlineelectoralsystem.model.VoteCount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VoteProcessRepository extends JpaRepository<VoteCount,String> {
    Optional<VoteCount> findBySymbol(String symbol);
    Optional<VoteCount> findByPositionIdAndSymbol(String positionId, String symbol);

}
