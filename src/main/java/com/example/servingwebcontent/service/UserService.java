package com.example.servingwebcontent.service;

import com.example.servingwebcontent.model.User;

import java.util.List;

public interface UserService {
    Iterable<User> allUsers();
    void add(User user);
    void delete(User user);
    void edit(User user);
    User getById(int id) throws Exception;
}