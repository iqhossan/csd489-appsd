package com.user.Service.controller;

import com.user.Service.dto.UserDTO;
import com.user.Service.helper.UserFoundException;
import com.user.Service.model.Role;
import com.user.Service.model.User;
import com.user.Service.model.UserRole;
import com.user.Service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

//    @Autowired
//    private PasswordEncoder passwordEncoder;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @GetMapping("/test")
    public String test(){
        return "Welcome to Backend API of Dental Surgery portal";
    }

    //creating user
    @PostMapping("/register")
    public User createUser(@RequestBody UserDTO userDTO) throws Exception {
        User user = new User();
        user.setFirstname(userDTO.getFirstname());
        user.setLastname(userDTO.getLastname());
        user.setUsername(userDTO.getUsername());
        user.setPassword(this.bCryptPasswordEncoder.encode(userDTO.getPassword()));
        user.setEmail(userDTO.getEmail());
        user.setWhomId(userDTO.getWhomId());

        Role role =new Role();
        role.setRoleId(userDTO.getRoleId());
        if(userDTO.getRoleId() == 44){
            role.setRoleName("PATIENT");
        }else if(userDTO.getRoleId() == 43){
            role.setRoleName("DENTIST");
        }else {
            role.setRoleName("ADMIN");
        }

        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);

        Set<UserRole> roles = new HashSet<>();
        roles.add(userRole);
        return this.userService.createUser(user,roles);
    }

    @GetMapping("/")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> user = this.userService.getAllUsers();
        return ResponseEntity.ok(user);
    }

    @GetMapping("/{username}")
    public User getUser(@PathVariable("username") String username){
        return this.userService.getUser(username);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable("userId") Long userId){
        this.userService.deleteUser(userId);
    }

    @ExceptionHandler(UserFoundException.class)
    public ResponseEntity<?>exceptionHandler(UserFoundException e){
        String errorMessage = "User not found: " + e.getMessage();
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }
}
