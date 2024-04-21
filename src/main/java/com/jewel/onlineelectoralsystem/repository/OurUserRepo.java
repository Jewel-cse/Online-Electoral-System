package com.jewel.onlineelectoralsystem.repository;


import com.jewel.onlineelectoralsystem.model.OurUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface OurUserRepo extends JpaRepository<OurUsers,Integer> {
    Optional<OurUsers> findByVoterId(String voterId);
}
