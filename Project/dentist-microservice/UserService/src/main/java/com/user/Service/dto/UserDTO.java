package com.user.Service.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.user.Service.model.Authority;
import com.user.Service.model.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO{

    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String email;
    private Long roleId;
    private String roleName;
    private int whomId;
}
