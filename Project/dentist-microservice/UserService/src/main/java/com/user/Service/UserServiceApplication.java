package com.user.Service;

import com.user.Service.helper.UserFoundException;
import com.user.Service.model.Role;
import com.user.Service.model.User;
import com.user.Service.model.UserRole;
import com.user.Service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class UserServiceApplication implements CommandLineRunner {

	@Autowired
	private UserService userService;

//	@Autowired
//	private PasswordEncoder passwordEncoder;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		try {
			System.out.println("Starting Code");

			User user = new User();
			user.setFirstname("Iqbal");
			user.setLastname("Hossain");
			user.setUsername("admin");
			user.setPassword(bCryptPasswordEncoder.encode("123"));
			user.setEmail("admin@gmail.com");

			Role role1 = new Role();
			role1.setRoleId(44L);
			role1.setRoleName("ADMIN");

			Set<UserRole> userRoleSet = new HashSet<>();
			UserRole userRole = new UserRole();
			userRole.setRole(role1);
			userRole.setUser(user);
			userRoleSet.add(userRole);

			User user1 = this.userService.createUser(user, userRoleSet);
			System.out.println(user1.getUsername());
		}catch (UserFoundException e){
			e.printStackTrace();
		}
	}
}
