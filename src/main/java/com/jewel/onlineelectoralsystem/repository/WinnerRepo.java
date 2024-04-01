package com.jewel.onlineelectoralsystem.repository;

import com.jewel.onlineelectoralsystem.model.Winner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WinnerRepo extends JpaRepository<Winner,String> {
}
