package com.jewel.onlineelectoralsystem.controller;


import com.jewel.onlineelectoralsystem.dto.ReqRes;
import com.jewel.onlineelectoralsystem.service.AuthService;
import com.jewel.onlineelectoralsystem.service.JWTUtils;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @PostMapping("/sign-up")
    public ResponseEntity<ReqRes> signup(@RequestBody ReqRes signupRequest){
        return ResponseEntity.ok(authService.signUp(signupRequest));
    }
    @PostMapping("/sign-in")
    @ResponseBody
    public ResponseEntity<ReqRes> signin(@RequestBody ReqRes signinRequest,HttpServletResponse response){
        ReqRes reqRes = authService.signIn(signinRequest);

        response.addCookie(authService.createSecureCookie("accessToken",reqRes.getToken(),JWTUtils.EXPIRATION_TIME/1000));
        response.addCookie(authService.createSecureCookie("refreshToken",reqRes.getRefreshToken(),JWTUtils.EXPIRATION_TIME_REFRESH/1000));

        return ResponseEntity.ok(reqRes);
    }

    @PostMapping("/refresh")
    public ResponseEntity<ReqRes> refresh(@RequestBody ReqRes refreshTokenRequest){
        return ResponseEntity.ok(authService.refreshToken(refreshTokenRequest));
    }


}
