package com.example.servingwebcontent.service;


import com.example.servingwebcontent.model.User;
import com.example.servingwebcontent.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepo userRepo;

    @Autowired
    public void setUserDAO(UserRepo userRepo) {
        this.userRepo = userRepo;
    }


    @Transactional(readOnly = true)
    @Override
    public Iterable<User> allUsers() {
        return userRepo.findAll();
    }

    @Transactional
    @Override
    public void add(User user) {
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
}
