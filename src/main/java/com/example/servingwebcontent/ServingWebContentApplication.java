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
		users.addAdmin(new User("27","Ivan","admin","admin"));
		users.add(new User("17","Fedot","user","user"));

	}
}
