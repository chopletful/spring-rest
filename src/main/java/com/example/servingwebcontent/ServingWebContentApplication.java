package com.example.servingwebcontent;

import com.example.servingwebcontent.model.Role;
import com.example.servingwebcontent.model.User;
import com.example.servingwebcontent.repos.RoleRepo;
import com.example.servingwebcontent.service.UserService;
import com.example.servingwebcontent.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class ServingWebContentApplication implements CommandLineRunner {
	@Autowired
	private  UserServiceImpl users;

	@Autowired
	private  RoleRepo roles;

	public static void main(String[] args) {

		SpringApplication.run(ServingWebContentApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {



		roles.save(new Role(1,"ROLE_ADMIN"));
		roles.save(new Role(2,"ROLE_USER"));

		Set<Role> roleSet = users.getSetOfRoles(Collections.singletonList("1"));
		Set<Role> roleSet1 = users.getSetOfRoles(Collections.singletonList("2"));

		User admin = new User("27","Ivan","Fedotov","admin","admin");
		User user = new User("17","Fedot","Ivanov","user","user");

		admin.setRoles(roleSet);
		user.setRoles(roleSet1);
		users.add(admin);
		users.add(user);

	}
}
