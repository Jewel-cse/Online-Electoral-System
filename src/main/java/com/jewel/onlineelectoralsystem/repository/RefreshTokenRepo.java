package com.jewel.onlineelectoralsystem.repository;

import com.jewel.onlineelectoralsystem.model.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokenRepo extends JpaRepository<RefreshToken,String> {
    RefreshToken findByVoterId(String voterId);
    void  deleteByVoterId(String voterId);
}
