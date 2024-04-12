package com.identity.config;

import com.identity.entity.UserCredential;
import com.identity.repository.UserCredentialRepository;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomerUserDetailsService implements UserDetailsService {

    @Autowired
    private UserCredentialRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //System.out.println("Check Username: "+username);
        Optional<UserCredential> credential = repository.findByName(username);
        return credential.map(CustomerUserDetails::new).orElseThrow(()->new UsernameNotFoundException("user name not found with name "+ username));
    }
}
