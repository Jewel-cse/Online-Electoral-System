package com.jewel.onlineelectoralsystem.service;


import com.jewel.onlineelectoralsystem.repository.OurUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class OurUserDetailsService implements UserDetailsService {
    @Autowired
    private OurUserRepo ourUserRepo;
    @Override
    public UserDetails loadUserByUsername(String voterId) throws UsernameNotFoundException {
        return ourUserRepo.findByVoterId(voterId).orElseThrow();
    }
}
