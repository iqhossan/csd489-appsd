package com.user.Service.controller;

import com.user.Service.dto.AuthRequest;
import com.user.Service.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService service;
    @Autowired
    private AuthenticationManager authenticationManager;

//    @PostMapping("/register")
//    public String addNewUser(@RequestBody UserCredential user){
//        return service.saveUser(user);
//    }

    @PostMapping("/token")
    public String getToken(@RequestBody AuthRequest authRequest){
        System.out.println("con :"+authRequest.getUsername());
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(),authRequest.getPassword()));
        if(authenticate.isAuthenticated()){
            return service.generateToken(authRequest.getUsername());
        }
        else{
            throw new RuntimeException("User not found");
        }
    }

    @GetMapping("/validate")
    public String validateToken(@RequestParam("token") String token){
        service.validateToken(token);
        return "token is valid";
    }
}
