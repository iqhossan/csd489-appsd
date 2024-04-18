package com.user.Service.controller;

import com.user.Service.dto.AuthRequest;
import com.user.Service.dto.JwtResponse;
import com.user.Service.model.User;
import com.user.Service.service.AuthService;
import com.user.Service.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/old-auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private AuthService service;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

//    @PostMapping("/register")
//    public String addNewUser(@RequestBody UserCredential user){
//        return service.saveUser(user);
//    }


    @PostMapping("/token")
    public ResponseEntity<JwtResponse> getToken(@RequestBody AuthRequest authRequest){
        //System.out.println("con :"+authRequest.getUsername());
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(),authRequest.getPassword()));
        if(authenticate.isAuthenticated()){
            UserDetails userDetails = this.userDetailsServiceImpl.loadUserByUsername(authRequest.getUsername());
           //System.out.println(userDetails.getUsername());
            String token = this.service.generateToken(userDetails.getUsername());
            return new ResponseEntity<>(new JwtResponse(token), HttpStatus.OK);
            //return service.generateToken(authRequest.getUsername());
        }
        else{
            throw new RuntimeException("User not found");
        }
    }

/*
    @PostMapping("/generate-token")
    public ResponseEntity<JwtResponse> generateToken(@RequestBody AuthRequest authRequest) throws Exception{
        try{
            this.authenticate(authRequest.getUsername(),authRequest.getPassword());
        }catch(UserNotFoundException e){
            e.printStackTrace();
            throw new Exception("User not found");
        }

        UserDetails userDetails = this.userDetailsServiceImpl.loadUserByUsername(authRequest.getUsername());
        String token = this.service.generateToken(userDetails.getUsername());
        return new ResponseEntity<>(new JwtResponse(token), HttpStatus.OK);
    }


    private void authenticate(String username,String password) throws Exception {
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        }catch (DisabledException e){
            throw new Exception("User Disabled " + e.getMessage());
        }catch (BadCredentialsException e){
            throw new Exception("Invalid Credentials "+ e.getMessage());
        }
    }
*/

    @GetMapping("/validate")
    public String validateToken(@RequestParam("token") String token){
        service.validateToken(token);
        return "token is valid";
    }

    //return the details of current user who loggedIn
    @GetMapping("/current-user")
    public User getCurrentUser(Principal principal){
        //System.out.println(principal.getName());
        return (User) this.userDetailsServiceImpl.loadUserByUsername(principal.getName());
    }
}
