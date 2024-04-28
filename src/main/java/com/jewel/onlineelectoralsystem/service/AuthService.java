package com.jewel.onlineelectoralsystem.service;


import com.jewel.onlineelectoralsystem.dto.ReqRes;
import com.jewel.onlineelectoralsystem.model.OurUsers;
import com.jewel.onlineelectoralsystem.model.RefreshToken;
import com.jewel.onlineelectoralsystem.repository.OurUserRepo;
import com.jewel.onlineelectoralsystem.repository.RefreshTokenRepo;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class AuthService {
    @Autowired
    private OurUserRepo ourUserRepo;
    @Autowired
    private JWTUtils jwtUtils;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RefreshTokenRepo refreshTokenRepo;

    public ReqRes signUp(ReqRes registrationRequest){
        ReqRes resp = new ReqRes();
        try{
            if(ourUserRepo.findByVoterId(registrationRequest.getVoterId()).orElse(null) != null){
                resp.setMessage("Already sign up");
                resp.setStatusCode(409);
                return  resp;
            }
            OurUsers ourUsers = new OurUsers();
            //ourUsers.setId(null);
            ourUsers.setVoterId(registrationRequest.getVoterId());
            ourUsers.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
            ourUsers.setRole(registrationRequest.getRole());

            OurUsers ourUserResult = ourUserRepo.save(ourUsers);
            if(ourUserResult != null ){
                resp.setOurUsers(ourUserResult);
                resp.setMessage("User saved successfully");
                resp.setStatusCode(200);
            }
        }catch (Exception e){
            resp.setStatusCode(500);
            resp.setError(e.getMessage());
        }
        return  resp;
    }

    public ReqRes signIn(ReqRes signinRequest){
        ReqRes response = new ReqRes();
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signinRequest.getVoterId(),signinRequest.getPassword()));

            var user = ourUserRepo.findByVoterId(signinRequest.getVoterId()).orElseThrow(); //wrong password or email hole throw korbe

            var jwt = jwtUtils.generateToken(user);
            var refreshToken = jwtUtils.generateRefreshToken(new HashMap<>(),user);

            response.setStatusCode(200);
            response.setToken(jwt);
            response.setRefreshToken(refreshToken);
            response.setExpireTime("24Hr");
            response.setMessage("successfully signed In");
            RefreshToken existingRefreshToken = refreshTokenRepo.findByVoterId(user.getVoterId());
            if(existingRefreshToken != null) {
                existingRefreshToken.setRefreshToken(refreshToken);
                refreshTokenRepo.save(existingRefreshToken);
            }else{
                refreshTokenRepo.save(new RefreshToken(refreshToken,user.getVoterId()));
            }
            return response;
        }catch (Exception e){
            response.setStatusCode(500);
            response.setError(e.getMessage());
        }
        return response;
    }

    public ReqRes refreshToken(ReqRes refreshTokenRequest){ //ekta refresh token asbe
        ReqRes response = new ReqRes();
        try
        {
            String ourVoterId = jwtUtils.extractUserName(refreshTokenRequest.getRefreshToken()); //extract voterId
            OurUsers users = ourUserRepo.findByVoterId(ourVoterId).orElseThrow();
            if(users != null && jwtUtils.isTokenValid(refreshTokenRequest.getRefreshToken(),users)){
                var jwt = jwtUtils.generateToken(users);
                var refreshToken = jwtUtils.generateRefreshToken(new HashMap<>(),users);
                response.setStatusCode(200);
                response.setToken(jwt);
                response.setRefreshToken(refreshToken);
                response.setExpireTime("1 minutes");
                response.setMessage("successfully refreshed token");

                createSecureCookie("accessToken",jwt,JWTUtils.EXPIRATION_TIME/1000);
                createSecureCookie("refreshToken",refreshToken,JWTUtils.EXPIRATION_TIME_REFRESH/1000);
//
//                refreshTokenRepo.deleteByVoterId(ourVoterId);
//                refreshTokenRepo.save(new RefreshToken(refreshToken,users.getVoterId()));
//                return response;
            }else {
                response.setStatusCode(422);
                response.setMessage("UnProcessed-able entity");
            }
            return  response;
        }
        catch (Exception e){
            response.setStatusCode(500);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    // Helper method for creating secure cookies
    public Cookie createSecureCookie(String name, String value, long maxAgeSeconds) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setHttpOnly(true); // Prevent JavaScript access
        cookie.setSecure(true);  // Send only over HTTPS (recommended)
        cookie.setMaxAge((int) maxAgeSeconds);
        return cookie;
    }

}
