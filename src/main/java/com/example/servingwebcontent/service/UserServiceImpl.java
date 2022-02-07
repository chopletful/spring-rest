package com.example.servingwebcontent.service;


import com.example.servingwebcontent.model.Role;
import com.example.servingwebcontent.model.User;
import com.example.servingwebcontent.repos.RoleRepo;
import com.example.servingwebcontent.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;


@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepo userRepo;


    @Autowired
    RoleRepo roleRepository;


    @Transactional(readOnly = true)
    @Override
    public Iterable<User> allUsers() {
        return userRepo.findAll();
    }

    @Transactional
    @Override
    public void add(User user) {
        user.setRoles(Collections.singleton(new Role(1, "ROLE_USER")));
        userRepo.save(user);
    }

    @Transactional
    @Override
    public void delete(User user) {
        userRepo.delete(user);
    }

    @Transactional
    @Override
    public void edit(User user) {
        userRepo.save(user);
    }

    @Override
    public User getById(int id) throws Exception {
        return userRepo.findById(id).orElseThrow(() -> new Exception("No user found"));
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userRepo.findByLogin(login);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }

}
