package com.user.Service.service;

import com.user.Service.model.User;
import com.user.Service.model.UserRole;

import java.util.List;
import java.util.Set;

public interface UserService {

    //Creating user
    public User createUser(User user, Set<UserRole> userRoles) throws Exception;
    public User getUser(String username);

    public void deleteUser(Long userId);
    List<User> getAllUsers();
}
