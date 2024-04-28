package com.jewel.onlineelectoralsystem.service;

import com.jewel.onlineelectoralsystem.dto.ReqRes;
import com.jewel.onlineelectoralsystem.model.OurUsers;
import com.jewel.onlineelectoralsystem.repository.OurUserRepo;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
//@Component
public class RefreshTokenInterceptor implements HandlerInterceptor {
    @Autowired
    private AuthService authService;

    @Autowired
    private  JWTUtils jwtUtils;
    @Autowired
    private  OurUserRepo ourUserRepo;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("Calling prehandle-----------");
        String authorizationHeader = request.getHeader("Authorization");

        // Check for presence and format of Authorization header
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);

            try{
                String voterId = jwtUtils.extractUserName(token);
                OurUsers user = ourUserRepo.findByVoterId(voterId).orElse(null);
                // Validate access token
                if (user != null && jwtUtils.isTokenValid(token,user)) {
                    return true; // Allow request to proceed with valid access token
                }

            }catch (Exception e){

                String refreshToken = getRefreshTokenFromCookie(request); // Extract refresh token from cookie
                if (refreshToken != null) {
                    ReqRes req = new ReqRes();
                    req.setRefreshToken(refreshToken);
                    ReqRes refreshResponse = authService.refreshToken(req);
                    System.out.println("status code :::::::: "+refreshResponse.getStatusCode());
                    if (refreshResponse.getStatusCode() == 200) {
                        // Refresh successful, update Authorization header with new token
                        String newToken = refreshResponse.getToken();
                        response.addHeader("Authorization", "Bearer " + newToken);
                        System.out.println("Bearer token add to header----");
                        return true; // Allow request to proceed with refreshed token
                    }
                }
            }
        }

        // Access token is invalid and refresh failed or not present, return unauthorized
        response.sendError(HttpStatus.UNAUTHORIZED.value(), "Invalid or expired access token");
        return false;
    }
    // Helper method to extract refresh token from cookie (implementation details)
    private String getRefreshTokenFromCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("refreshToken")) {
                    System.out.println("get refresh token------");
                    return cookie.getValue();
                }
            }
        }
        System.out.println("no refresh token in cookies---------");
        return null;
    }
}
