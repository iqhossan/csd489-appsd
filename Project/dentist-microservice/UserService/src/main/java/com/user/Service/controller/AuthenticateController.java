package com.user.Service.controller;

import com.user.Service.config.JwtUtil;
import com.user.Service.helper.UserNotFoundException;
import com.user.Service.dto.AuthRequest;
import com.user.Service.dto.JwtResponse;
import com.user.Service.model.User;
import com.user.Service.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@CrossOrigin("*")
public class AuthenticateController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;
    @Autowired
    private JwtUtil jwtUtils;

    //generate Token
    @RequestMapping(value="/auth/generate-token", method = RequestMethod.POST)
    public ResponseEntity<JwtResponse> generateToken(@RequestBody AuthRequest jwtRequest) throws Exception{
        try{
            this.authenticate(jwtRequest.getUsername(),jwtRequest.getPassword());
        }catch(UserNotFoundException e){
            e.printStackTrace();
            throw new Exception("User not found");
        }

        UserDetails userDetails = this.userDetailsServiceImpl.loadUserByUsername(jwtRequest.getUsername());
        String token = this.jwtUtils.generateToken(userDetails);
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

    //return the details of current user who loggedIn
    @GetMapping("/auth/current-user")
    public User getCurrentUser(Principal principal){
        return (User) this.userDetailsServiceImpl.loadUserByUsername(principal.getName());
    }
}
