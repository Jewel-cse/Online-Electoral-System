package com.jewel.onlineelectoralsystem.repository;

import com.jewel.onlineelectoralsystem.model.VoteCount;
import com.jewel.onlineelectoralsystem.utility.VoteCountKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VoteCountRepository extends JpaRepository<VoteCount, VoteCountKey> {
    //Optional<VoteCount>  findByIdPositionIdAndIdSymbol(String positionId, String symbol);
    Optional<List<VoteCount>> findByIdPositionId(String positionId);
}
