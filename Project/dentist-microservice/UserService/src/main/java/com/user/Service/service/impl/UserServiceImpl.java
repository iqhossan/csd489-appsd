package com.user.Service.service.impl;

import com.user.Service.helper.UserFoundException;
import com.user.Service.model.User;
import com.user.Service.model.UserRole;
import com.user.Service.repo.RoleRepository;
import com.user.Service.repo.UserRepository;
import com.user.Service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public User createUser(User user, Set<UserRole> userRoles) throws Exception {
        User local = this.userRepository.findByUsername(user.getUsername());
        if(local != null){
            System.out.println("User is already there");
            throw new UserFoundException();
        }else{
            //save all roles
            for(UserRole ur:userRoles){
                roleRepository.save(ur.getRole());
            }
            //assign all roles with users
            user.getUserRoles().addAll(userRoles);
            local = this.userRepository.save(user);
        }
        return local;
    }

    @Override
    public User getUser(String username){
        return this.userRepository.findByUsername(username);
    }

    @Override
    public void deleteUser(Long userId){
        this.userRepository.deleteById(userId);
    }

    @Override
    public List<User> getAllUsers() {
        return this.userRepository.findAll(Sort.by("id").descending());
    }

}
